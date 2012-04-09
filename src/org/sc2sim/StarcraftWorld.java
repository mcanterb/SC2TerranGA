package org.sc2sim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.sc2sim.commands.BuildSCV;
import org.sc2sim.commands.CollectMinerals;
import org.sc2sim.commands.DoNothing;
import org.sc2sim.commands.GatherGas;
import org.sc2sim.genetic.Chromosome;
import org.sc2sim.genetic.Gene;
import org.sc2sim.units.Barracks;
import org.sc2sim.units.CommandCenter;
import org.sc2sim.units.Marauder;
import org.sc2sim.units.Marine;
import org.sc2sim.units.SCV;

public class StarcraftWorld 
{
	private int minerals;
	private int gas;
	private ArrayList<Unit> units;
	private float time;
	private int supply;
	private int takenSupply;
	private PriorityQueue<ScheduledAction> events;
	private HashMap<Unit, ScheduledAction> unitActions;
	private TechTree tech;
	private boolean logging = false;
	private BufferedWriter writer;
	
	public static final int SELECTION_SCV = 0;
	public static final int SELECTION_CC = 1;
	public static final int SELECTION_RAX = 2;

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StarcraftWorld world = new StarcraftWorld();
		world.setChromosome(new Chromosome(300));
		world.enableLogging();
		world.run(300);
		
		System.out.println("Supply: "+world.takenSupply);
		System.out.println("Minerals: "+world.minerals);
		world.flushLog();

	}
	
	public void enableLogging()
	{
		logging = true;
	}
	
	public void disableLogging()
	{
		logging = false;
	}
	
	public void flushLog()
	{
		try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setChromosome(Chromosome c)
	{
		for(int i = 0; i < c.getLength(); i++)
		{
			scheduleAction(i/2.0f, new GeneAction(c.getGene(i)), null);
		}
	}
	
	public StarcraftWorld()
	{
		tech = new TechTree();
		try {
			writer = new BufferedWriter(new FileWriter("best.log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetWorld();
	}
	
	
	public void run(float until)
	{
		while(!events.isEmpty())
		{
			ScheduledAction event = events.poll();
			time = event.time;
			if(time > until) return;
			event.a.performAction(this, event.unit);
		}
		if(logging)
			try {
				writer.write(units.toString()+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void resetWorld()
	{
		minerals = 50;
		gas = 0;
		units = new ArrayList<Unit>();
		events = new PriorityQueue<ScheduledAction>();
		supply = 11;
		takenSupply = 5;
		unitActions = new HashMap<Unit, ScheduledAction>();
		tech.reset();
		time = 0;
		Barracks.num = 0;
		Marine.num = 0;
		SCV.num = 0;
		Marauder.num = 0;
		
		
		
		units.add(new CommandCenter());
		for(int i = 0; i < 5; i++)
		{
			SCV scv = new SCV();
			units.add(scv);
			CollectMinerals.INSTANCE.issue(this, scv);
		}
		//BuildSCV.INSTANCE.issue(this, units.get(0));
	}
	
	public void cancelUnitAction(Unit unit)
	{
		ScheduledAction sa = unitActions.get(unit);
		if(sa!=null)
		{
			events.remove(sa);
			unitActions.put(unit, null);
		}
	}
	
	public int getMinerals()
	{
		return minerals;
	}
	
	public TechTree getTechTree()
	{
		return tech;
	}
	
	public int getGas()
	{
		return gas;
	}
	
	public void setMinerals(int num)
	{
		minerals = num;
	}
	
	public void setGas(int num)
	{
		gas = num;
	}
	
	public void addUnit(Unit u)
	{
		units.add(u);
	}
	
	public void log(String message)
	{
		if(logging)
		{
			try {
				writer.write(time + " - "+message+"\n");
				writer.write("Minerals: "+minerals + ". Gas: " + gas + ". Supply: "+takenSupply+"/"+supply+"\n\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void scheduleAction(float time, Action a, Unit unit)
	{
		ScheduledAction sa = new ScheduledAction(time, a, unit);
		events.add(sa);
		unitActions.put(unit, sa);
	}

	public float getTime() {
		return time;
	}
	
	public void setSupply(int supply) {
		this.supply = supply;
	}

	public int getSupply() {
		return supply;
	}

	public void setTakenSupply(int takenSupply) {
		this.takenSupply = takenSupply;
	}

	public int getTakenSupply() {
		return takenSupply;
	}

	class ScheduledAction implements Comparable<ScheduledAction>
	{
		public Action a;
		public float time;
		public Unit unit;
		
		public ScheduledAction(float time, Action a, Unit unit)
		{
			this.a = a;
			this.time = time;
			this.unit = unit;
		}

		@Override
		public int compareTo(ScheduledAction o) {
			return (time < o.time)?-1:(time > o.time)?1:0;
		}

	}
	
	public void removeUnit(Unit u)
	{
		cancelUnitAction(u);
		units.remove(u);
	}
	
	private Unit selectSCV()
	{
		for(Unit unit : units)
		{
			if(unit instanceof SCV)
			{
				if(unit.getCommand() == CollectMinerals.INSTANCE ||
				   unit.getCommand() == GatherGas.INSTANCE) return unit;
			}
		}
		return null;
	}
	
	public ArrayList<Unit> getUnits()
	{
		return units;
	}
	
	private Unit selectCC()
	{
		for(Unit unit : units)
		{
			if(unit instanceof CommandCenter)
			{
				return unit;
			}
		}
		return null;
	}
	
	private Unit selectRax()
	{
		for(Unit unit : units)
		{
			if(unit instanceof Barracks)
			{
				if(unit.getCommand() == DoNothing.INSTANCE) return unit;
			}
		}
		return null;
	}
	
	public class GeneAction implements Action {

		Gene g;
		
		public GeneAction(Gene g)
		{
			this.g = g;
		}
		
		@Override
		public void performAction(StarcraftWorld world, Unit unit) 
		{
			switch(g.getSelection())
			{
			case SELECTION_SCV:
				unit = selectSCV();
				if(unit == null) return;
				break;
			case SELECTION_CC:
				unit = selectCC();
				if(unit == null) return;
				break;
			case SELECTION_RAX:
				unit = selectRax();
				if(unit == null) return;
				break;
			}
			ArrayList<Command> commands = unit.getSupportedCommands();
			Command command = commands.get(1+Math.abs(g.getAction()%(commands.size()-1)));
			if(command.canIssue(world, unit))
			{
				command.issue(world, unit);
				log("Issued Command: "+command.getClass().getSimpleName() + " with unit "+unit);
			}

		}

	}


	
}

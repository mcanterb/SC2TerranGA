package org.sc2sim;

import java.util.ArrayList;

import org.sc2sim.commands.DoNothing;

public abstract class Unit {
	
	protected Command command;
	private int energy=0;
	private float creationTime;
	
	public Unit()
	{
		command = DoNothing.INSTANCE;
	}
	
	public float getCreationTime()
	{
		return creationTime;
	}
	
	public void setCreationTime(float time)
	{
		creationTime = time;
	}
	
	public int getEnergy(){return energy;}
	public void setEnergy(int e){energy = e;}
	
	public abstract float getDPS();
	public abstract int getHP();
	public abstract int getArmor();
	public abstract int getBuildTime();
	public ArrayList<Command> getDoableCommands(StarcraftWorld world)
	{
		ArrayList<Command> commands = getSupportedCommands();
		ArrayList<Command> ret = new ArrayList<Command>();
		for(int i = 0; i < commands.size(); i++)
		{
			if(commands.get(i).canIssue(world, this)) ret.add(commands.get(i));
		}
		return ret;
	}
	
	public abstract ArrayList<Command> getSupportedCommands();
	
	public void setCommand(Command c)
	{
		command = c;
	}
	
	public Command getCommand()
	{
		return command;
	}
	
	public String toString()
	{
		return this.getClass().getSimpleName();
	}
	
	
}

package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildBarracks;
import org.sc2sim.commands.BuildRefinery;
import org.sc2sim.commands.BuildSupplyDepot;
import org.sc2sim.commands.CollectMinerals;
import org.sc2sim.commands.DoNothing;
import org.sc2sim.commands.GatherGas;

public class SCV extends Unit {

	public static final ArrayList<Command> supportedCommands;
	
	private int thisNum;
	public static int num = 0;
	
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
		supportedCommands.add(CollectMinerals.INSTANCE);
		supportedCommands.add(BuildSupplyDepot.INSTANCE);
		supportedCommands.add(BuildBarracks.INSTANCE);
		supportedCommands.add(BuildRefinery.INSTANCE);
		supportedCommands.add(GatherGas.INSTANCE);
	}
	
	
	public SCV()
	{
		thisNum = num++;
	}
	
	@Override
	public float getDPS() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBuildTime() {
		// TODO Auto-generated method stub
		return 17;
	}


	@Override
	public ArrayList<Command> getSupportedCommands() {
		return supportedCommands;
	}

	public String toString()
	{
		return "SCV" + thisNum;
	}
	
}

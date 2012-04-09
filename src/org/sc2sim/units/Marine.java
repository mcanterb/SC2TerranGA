package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildBarracks;
import org.sc2sim.commands.BuildSupplyDepot;
import org.sc2sim.commands.CollectMinerals;
import org.sc2sim.commands.DoNothing;

public class Marine extends Unit {

public static final ArrayList<Command> supportedCommands;
	
	public static int num = 0;
	private int thisNum;
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
	}
	
	public Marine()
	{
		thisNum = num++;
	}
	
	@Override
	public float getDPS() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 45;
	}

	@Override
	public int getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBuildTime() {
		// TODO Auto-generated method stub
		return 25;
	}

	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}

	public String toString()
	{
		return "Marine"+thisNum;
	}
	
}

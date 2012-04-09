package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildBarracks;
import org.sc2sim.commands.BuildSupplyDepot;
import org.sc2sim.commands.CollectMinerals;
import org.sc2sim.commands.DoNothing;

public class Marauder extends Unit {

public static final ArrayList<Command> supportedCommands;
	
	public static int num = 0;
	private int thisNum;
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
	}
	
	public Marauder()
	{
		thisNum = num++;
	}
	
	@Override
	public float getDPS() {
		// TODO Auto-generated method stub
		return 13.4f;
	}

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 125;
	}

	@Override
	public int getArmor() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getBuildTime() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}

	public String toString()
	{
		return "Marauder"+thisNum;
	}
	
}

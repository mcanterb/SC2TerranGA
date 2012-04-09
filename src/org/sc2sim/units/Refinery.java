package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.DoNothing;

public class Refinery extends Unit {

	public static final ArrayList<Command> supportedCommands;
	private int numOfCollectors = 0;

	static{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
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
		return 0;
	}

	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}

	public void setNumOfCollectors(int numOfCollectors) {
		this.numOfCollectors = numOfCollectors;
	}

	public int getNumOfCollectors() {
		return numOfCollectors;
	}

}

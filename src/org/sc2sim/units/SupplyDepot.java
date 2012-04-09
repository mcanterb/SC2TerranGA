package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildSCV;
import org.sc2sim.commands.CallDownMULE;
import org.sc2sim.commands.DoNothing;

public class SupplyDepot extends Unit {

public static final ArrayList<Command> supportedCommands;
	
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
	}

	private boolean extraSupply = false;
	
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
	
	public boolean hasExtraSupply()
	{
		return extraSupply ;
	}

	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}

	public void callDownSupply() {
		extraSupply = true;
		
	}

}

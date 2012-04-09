package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildSCV;
import org.sc2sim.commands.DoNothing;
import org.sc2sim.commands.UpgradeOrbitalCommand;

public class CommandCenter extends Unit {
	
public static final ArrayList<Command> supportedCommands;
	
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
		supportedCommands.add(BuildSCV.INSTANCE);
		supportedCommands.add(UpgradeOrbitalCommand.INSTANCE);
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
		return 100;
	}


	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}


}

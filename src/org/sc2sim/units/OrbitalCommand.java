package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildSCV;
import org.sc2sim.commands.CallDownMULE;
import org.sc2sim.commands.CallDownSupply;
import org.sc2sim.commands.DoNothing;

public class OrbitalCommand extends CommandCenter {

	public static final ArrayList<Command> supportedCommands;
	
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
		supportedCommands.add(BuildSCV.INSTANCE);
		supportedCommands.add(CallDownMULE.INSTANCE);
		supportedCommands.add(CallDownSupply.INSTANCE);
	}
	
	public OrbitalCommand()
	{
		setEnergy(50);
	}
	
	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}

}

package org.sc2sim.commands;

import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;

public class DoNothing implements Command {
	public static final DoNothing INSTANCE = new DoNothing();
	
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return false;
	}
	

}

package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;

public class CollectMineralsMule implements Command {

	public static final CollectMineralsMuleAction action = new CollectMineralsMuleAction();
	public static final CollectMineralsMule INSTANCE = new CollectMineralsMule();
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.scheduleAction(world.getTime()+10.587f, action, issuer);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return issuer.getCommand()!=INSTANCE;
	}

	public static class CollectMineralsMuleAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			world.setMinerals(world.getMinerals()+30);
			world.scheduleAction(world.getTime()+10.587f, this, unit);
		}

	}

}

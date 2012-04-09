package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;

public class CollectMinerals implements Command {

	public static final CollectMineralsAction action = new CollectMineralsAction();
	public static final CollectMinerals INSTANCE = new CollectMinerals();
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.cancelUnitAction(issuer);
		world.scheduleAction(world.getTime()+7.2f, action, issuer);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return issuer.getCommand()!=INSTANCE;
	}

	public static class CollectMineralsAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			world.setMinerals(world.getMinerals()+5);
			world.scheduleAction(world.getTime()+7.2f, this, unit);
		}

	}
}

package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.Command;
import org.sc2sim.units.Marine;

public class BuildMarine implements Command{
	
	public static final BuildMarineAction action = new BuildMarineAction();
	public static final BuildMarine INSTANCE = new BuildMarine();
	public static final float Marine_BUILD_TIME = 25.0f;
	public static final int Marine_MINERAL_COST = 50;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.scheduleAction(world.getTime()+Marine_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-Marine_MINERAL_COST);
		world.setTakenSupply(world.getTakenSupply()+1);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= Marine_MINERAL_COST &&
		   world.getTakenSupply() < world.getSupply() &&
		   issuer.getCommand() == DoNothing.INSTANCE;
	}

	public static class BuildMarineAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			unit.setCommand(DoNothing.INSTANCE);
			Marine marine = new Marine();
			marine.setCreationTime(world.getTime());
			world.addUnit(marine);
			world.log(marine + " complete at " + unit);
		}

	}
	
}

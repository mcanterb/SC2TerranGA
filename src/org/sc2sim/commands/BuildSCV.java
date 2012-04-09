package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.units.SCV;

public class BuildSCV implements Command {

	public static final BuildSCVAction action = new BuildSCVAction();
	public static final BuildSCV INSTANCE = new BuildSCV();
	public static final float SCV_BUILD_TIME = 17.0f;
	public static final int SCV_MINERAL_COST = 50;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.scheduleAction(world.getTime()+SCV_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-SCV_MINERAL_COST);
		world.setTakenSupply(world.getTakenSupply()+1);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= SCV_MINERAL_COST &&
			   world.getTakenSupply() < world.getSupply()&&
			   issuer.getCommand()==DoNothing.INSTANCE;
	}

	public static class BuildSCVAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			unit.setCommand(DoNothing.INSTANCE);
			SCV scv = new SCV();
			world.addUnit(scv);
			CollectMinerals.INSTANCE.issue(world, scv);
			world.log(scv+"Complete!");
		}

	}
}




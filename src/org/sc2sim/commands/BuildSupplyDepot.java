package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildSCV.BuildSCVAction;
import org.sc2sim.units.SCV;

public class BuildSupplyDepot implements Command {

	public static final BuildSupplyDepot INSTANCE = new BuildSupplyDepot();
	public static final BuildSupplyDepotAction action = new BuildSupplyDepotAction();
	public static final float SUPPLY_DEPOT_BUILD_TIME = 30.0f;
	public static final int SUPPLY_DEPOT_MINERAL_COST = 100;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.cancelUnitAction(issuer);
		world.scheduleAction(world.getTime()+SUPPLY_DEPOT_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-SUPPLY_DEPOT_MINERAL_COST);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= SUPPLY_DEPOT_MINERAL_COST &&
		   issuer.getCommand() == CollectMinerals.INSTANCE;
	}

	public static class BuildSupplyDepotAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			world.setSupply(world.getSupply()+8);
			world.getTechTree().markSupplyDepot();
			CollectMinerals.INSTANCE.issue(world, unit);
			world.log("Supply Depot Complete!");
		}

	}

}

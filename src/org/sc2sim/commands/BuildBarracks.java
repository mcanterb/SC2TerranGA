package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildSCV.BuildSCVAction;
import org.sc2sim.units.Barracks;
import org.sc2sim.units.SCV;

public class BuildBarracks implements Command {

	public static final BuildBarracks INSTANCE = new BuildBarracks();
	public static final BuildBarracksAction action = new BuildBarracksAction();
	public static final float BARRACKS_BUILD_TIME = 60.0f;
	public static final int BARRACKS_MINERAL_COST = 150;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.cancelUnitAction(issuer);
		world.scheduleAction(world.getTime()+BARRACKS_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-BARRACKS_MINERAL_COST);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= BARRACKS_MINERAL_COST &&
			   world.getTechTree().isSupplyDepotBuilt() &&
			   issuer.getCommand() == CollectMinerals.INSTANCE;
	}

	public static class BuildBarracksAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			world.getTechTree().markBarracks();
			Barracks b = new Barracks();
			b.setCommand(DoNothing.INSTANCE);
			world.addUnit(b);
			CollectMinerals.INSTANCE.issue(world, unit);
			world.log(b + "Complete!");
		}

	}

}

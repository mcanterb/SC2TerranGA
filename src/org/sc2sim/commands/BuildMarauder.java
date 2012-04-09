package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildMarauder.BuildMarauderAction;
import org.sc2sim.units.Barracks;
import org.sc2sim.units.Marauder;

public class BuildMarauder implements Command {

	public static final BuildMarauderAction action = new BuildMarauderAction();
	public static final BuildMarauder INSTANCE = new BuildMarauder();
	public static final float Marauder_BUILD_TIME = 30.0f;
	public static final int Marauder_MINERAL_COST = 100;
	public static final int Marauder_GAS_COST = 25;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.scheduleAction(world.getTime()+Marauder_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-Marauder_MINERAL_COST);
		world.setGas(world.getGas()-Marauder_GAS_COST);
		world.setTakenSupply(world.getTakenSupply()+2);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= Marauder_MINERAL_COST &&
		   world.getGas() >= Marauder_GAS_COST &&
		   world.getTakenSupply() < world.getSupply()-1 &&
		   ((Barracks)issuer).hasTechLab() &&
		   issuer.getCommand() == DoNothing.INSTANCE;
	}

	public static class BuildMarauderAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			unit.setCommand(DoNothing.INSTANCE);
			Marauder marauder = new Marauder();
			marauder.setCreationTime(world.getTime());
			world.addUnit(marauder);
			world.log(marauder + " complete at " + unit);
		}

	}

}

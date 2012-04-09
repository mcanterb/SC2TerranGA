package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;

import org.sc2sim.units.OrbitalCommand;

public class UpgradeOrbitalCommand implements Command {
	public static final UpgradeOrbitalCommand INSTANCE = new UpgradeOrbitalCommand();
	public static final UpgradeOrbitalCommandAction action = new UpgradeOrbitalCommandAction();
	public static final OrbitalCommandEnergyAction energyAction = new OrbitalCommandEnergyAction();
	public static final float OC_BUILD_TIME = 35.0f;
	public static final int OC_MINERAL_COST = 150;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.scheduleAction(world.getTime()+OC_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-OC_MINERAL_COST);
		issuer.setCommand(this);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= OC_MINERAL_COST &&
			   world.getTechTree().isBarracksBuilt() &&
			   issuer.getCommand() == DoNothing.INSTANCE;
	}

	public static class UpgradeOrbitalCommandAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			OrbitalCommand oc = new OrbitalCommand();
			oc.setCommand(DoNothing.INSTANCE);
			world.removeUnit(unit);
			world.addUnit(oc);
			world.scheduleAction(world.getTime()+1.777f, energyAction, oc);
			world.log(oc + " Complete!");
		}
	}
	
	public static class OrbitalCommandEnergyAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			world.scheduleAction(world.getTime()+1.777f, energyAction, unit);
			if(unit.getEnergy()<200) unit.setEnergy(unit.getEnergy()+1);	
		}
	}

}

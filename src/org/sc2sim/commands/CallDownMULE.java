package org.sc2sim.commands;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.units.Mule;

public class CallDownMULE implements Command {

	public static final CallDownMULE INSTANCE = new CallDownMULE();
	public static final MuleAction action = new MuleAction();
	public static final float MULE_LIVE_TIME = 90.0f;
	public static final int MULE_ENERGY_COST = 50;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		issuer.setEnergy(issuer.getEnergy()-MULE_ENERGY_COST);
		world.setTakenSupply(world.getTakenSupply()+1);
		Mule mule = new Mule();
		world.addUnit(mule);
		world.scheduleAction(world.getTime()+MULE_LIVE_TIME, action, mule);
		CollectMineralsMule.INSTANCE.issue(world, mule);
	}

	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return issuer.getEnergy() >= MULE_ENERGY_COST;
	}

	static class MuleAction implements Action
	{

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			Mule mule = (Mule)unit;
			world.removeUnit(mule);
			world.log(mule + " broke down.");
		}
		
	}

}

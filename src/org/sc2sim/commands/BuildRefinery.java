package org.sc2sim.commands;

import java.util.ArrayList;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.units.Refinery;

public class BuildRefinery implements Command {

	public static final BuildRefinery INSTANCE = new BuildRefinery();
	public static final BuildRefineryAction action = new BuildRefineryAction();
	public static final float REFINERY_BUILD_TIME = 30.0f;
	public static final int REFINERY_MINERAL_COST = 75;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.cancelUnitAction(issuer);
		world.scheduleAction(world.getTime()+REFINERY_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-REFINERY_MINERAL_COST);
		issuer.setCommand(this);
	}

	public int getNumOfRefineries(ArrayList<Unit> units)
	{
		int count = 0;
		for(Unit unit: units)
		{
			if(unit instanceof Refinery)
			{
				count++;
			}
		}
		return count;
	
	}
	
	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= REFINERY_MINERAL_COST &&
			   issuer.getCommand() == CollectMinerals.INSTANCE &&
			   getNumOfRefineries(world.getUnits()) < 2;
	}

	public static class BuildRefineryAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			Refinery b = new Refinery();
			b.setCommand(DoNothing.INSTANCE);
			world.addUnit(b);
			GatherGas.INSTANCE.issue(world, unit);
			world.log(b + " Complete!");
		}

	}

}

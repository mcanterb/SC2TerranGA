package org.sc2sim.commands;

import java.util.ArrayList;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.units.Barracks;

public class BuildTechLab implements Command {

	public static final BuildTechLab INSTANCE = new BuildTechLab();
	public static final BuildTechLabAction action = new BuildTechLabAction();
	public static final float TL_BUILD_TIME = 25.0f;
	public static final int TL_MINERAL_COST = 50;
	public static final int TL_GAS_COST = 25;
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		issuer = getFreeBarracks(world.getUnits());
		world.scheduleAction(world.getTime()+TL_BUILD_TIME, action, issuer);
		world.setMinerals(world.getMinerals()-TL_MINERAL_COST);
		world.setGas(world.getGas()-TL_GAS_COST);
		issuer.setCommand(this);
	}

	private Barracks getFreeBarracks(ArrayList<Unit> units)
	{
		for(Unit u : units)
		{
			if(u instanceof Barracks)
			{
				Barracks b = (Barracks)u;
				if(b.getCommand() == DoNothing.INSTANCE &&
						!b.hasTechLab())
				{
					return b;
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return world.getMinerals() >= TL_MINERAL_COST &&
			   world.getGas() >= TL_GAS_COST &&
			   world.getTechTree().isBarracksBuilt() &&
			   issuer.getCommand() == DoNothing.INSTANCE &&
			   getFreeBarracks(world.getUnits()) != null;
	}

	public static class BuildTechLabAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			Barracks b = (Barracks)unit;
			b.setCommand(DoNothing.INSTANCE);
			b.setTechLab(true);
			world.log("Tech Lab on " + b + " Complete!");
		}
	}
	


}

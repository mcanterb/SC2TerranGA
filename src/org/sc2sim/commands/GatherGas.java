package org.sc2sim.commands;

import java.util.ArrayList;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.commands.GatherGas.GatherGasAction;
import org.sc2sim.units.Refinery;

public class GatherGas implements Command {

	public static final GatherGasAction action = new GatherGasAction();
	public static final GatherGas INSTANCE = new GatherGas();
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.cancelUnitAction(issuer);
		world.scheduleAction(world.getTime()+6.1f, action, issuer);
		issuer.setCommand(this);
		Refinery ref = getFreeRefinery(world.getUnits());
		ref.setNumOfCollectors(ref.getNumOfCollectors()+1);
		
				
	}

	private Refinery getFreeRefinery(ArrayList<Unit> units)
	{
		for(Unit unit : units)
		{
			if(unit instanceof Refinery)
			{
				Refinery ref = (Refinery)unit;
				if(ref.getNumOfCollectors() < 3) return ref;
			}
		}
		return null;
	}
	
	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return issuer.getCommand()==CollectMinerals.INSTANCE &&
		getFreeRefinery(world.getUnits())!= null;
	}

	public static class GatherGasAction implements Action {

		@Override
		public void performAction(StarcraftWorld world, Unit unit) {
			world.setGas(world.getGas()+4);
			world.scheduleAction(world.getTime()+6.1f, this, unit);
		}

	}
}

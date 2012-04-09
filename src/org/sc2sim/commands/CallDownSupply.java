package org.sc2sim.commands;

import java.util.ArrayList;

import org.sc2sim.Action;
import org.sc2sim.Command;
import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.commands.UpgradeOrbitalCommand.UpgradeOrbitalCommandAction;
import org.sc2sim.units.SupplyDepot;

public class CallDownSupply implements Command {
	public static final CallDownSupply INSTANCE = new CallDownSupply();
	
	@Override
	public void issue(StarcraftWorld world, Unit issuer) {
		world.setSupply(world.getSupply()+8);
		issuer.setEnergy(issuer.getEnergy()-50);
		for(Unit unit : world.getUnits())
		{
			if(unit instanceof SupplyDepot && !((SupplyDepot)unit).hasExtraSupply())
			{
				((SupplyDepot)unit).callDownSupply();
			}
		}

	}

	private boolean hasAvailableDepot(ArrayList<Unit> units)
	{
		for(Unit unit : units)
		{
			if(unit instanceof SupplyDepot && !((SupplyDepot)unit).hasExtraSupply())
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canIssue(StarcraftWorld world, Unit issuer) {
		return issuer.getEnergy() >= 50 && hasAvailableDepot(world.getUnits());
		
	}

	
}

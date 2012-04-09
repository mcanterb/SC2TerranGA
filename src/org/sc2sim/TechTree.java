package org.sc2sim;

public class TechTree {
	
	private boolean supplyDepot;
	private boolean barracks;
	
	
	public TechTree()
	{
		reset();
	}
	
	public void reset()
	{
		supplyDepot = false;
		barracks = false;
	}
	
	public boolean isSupplyDepotBuilt()
	{
		return supplyDepot;
	}
	
	public boolean isBarracksBuilt()
	{
		return barracks;
	}
	
	public void markSupplyDepot()
	{
		supplyDepot = true;
	}
	
	public void markBarracks()
	{
		barracks = true;
	}
	
	

}

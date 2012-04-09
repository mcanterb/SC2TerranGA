package org.sc2sim;

public interface Command {
	
	public void issue(StarcraftWorld world, Unit issuer);
	public boolean canIssue(StarcraftWorld world, Unit issuer);

}

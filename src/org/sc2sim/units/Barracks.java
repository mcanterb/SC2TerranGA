package org.sc2sim.units;

import java.util.ArrayList;

import org.sc2sim.Command;
import org.sc2sim.Unit;
import org.sc2sim.commands.BuildMarauder;
import org.sc2sim.commands.BuildMarine;
import org.sc2sim.commands.BuildSCV;
import org.sc2sim.commands.BuildTechLab;
import org.sc2sim.commands.DoNothing;

public class Barracks extends Unit {

public static final ArrayList<Command> supportedCommands;
	
	public static int num = 0;
	private int thisNum;
	
	private boolean hasTechLab = false;
	
	static
	{
		supportedCommands = new ArrayList<Command>();
		supportedCommands.add(DoNothing.INSTANCE);
		supportedCommands.add(BuildTechLab.INSTANCE);
		supportedCommands.add(BuildMarine.INSTANCE);
		supportedCommands.add(BuildMarauder.INSTANCE);
	}
	
	public Barracks()
	{
		thisNum = num++;
	}
	
	@Override
	public float getDPS() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBuildTime() {
		// TODO Auto-generated method stub
		return 60;
	}

	@Override
	public ArrayList<Command> getSupportedCommands() {
		// TODO Auto-generated method stub
		return supportedCommands;
	}

	public String toString()
	{
		return "Barracks"+thisNum;
	}

	public void setTechLab(boolean hasTechLab) {
		this.hasTechLab = hasTechLab;
	}

	public boolean hasTechLab() {
		return hasTechLab;
	}
	
}

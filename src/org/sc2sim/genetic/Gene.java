package org.sc2sim.genetic;

public class Gene {
	public static final int MOD_SELECTION=3;
	
	private int selection;
	private int action;
	
	public Gene(int selection, int action)
	{
		this.selection = Math.abs(selection%MOD_SELECTION);
		this.action = action;
	}
	
	public Gene mutate()
	{
		int selection = this.selection^Random.nextInt();
		int action = this.action^Random.nextInt();
		return new Gene(selection, action);
	}

	public int getSelection() {
		return selection;
	}

	public int getAction() {
		return action;
	}

}

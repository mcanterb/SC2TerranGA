package org.sc2sim.genetic;

import java.util.Arrays;

public class Chromosome implements Comparable<Chromosome> {
	
	private Gene[] genes;
	private float score = 0;
	
	public Chromosome(int numOfActions)
	{
		genes = new Gene[numOfActions];
		for(int i = 0; i < numOfActions; i++)
		{
			genes[i] = new Gene(Random.nextInt(), Random.nextInt());
		}
	}
	
	private Chromosome()
	{
		
	}
	
	public void setScore(float score)
	{
		this.score = score;
	}
	
	public float getScore()
	{
		return score;
	}

	public int compareTo(Chromosome c)
	{
		return (score < c.score?-1:(score==c.score?0:1));
	}
	
	public Gene getGene(int i) {
		// TODO Auto-generated method stub
		return genes[i];
	}
	
	public Chromosome crossover(float rate)
	{		
		Chromosome child = copy();
		for(int i = 0; i < child.genes.length; i+=5)
		{
			float rand = Random.nextFloat();
			if(rand <= rate)
			{
				if(Random.nextInt()%2==0)
				{
					Gene temp = child.genes[i];
					child.genes[i] = child.genes[i+1];
					child.genes[i+1] = child.genes[i+2];
					child.genes[i+2] = child.genes[i+3];
					child.genes[i+3] = child.genes[i+4];
					child.genes[i+4] = temp;
				}
				else
				{
					Gene temp = genes[i+4];
					child.genes[i+4] = child.genes[1+3];
					child.genes[i+3] = child.genes[i+2];
					child.genes[i+2] = child.genes[i+1];
					child.genes[i+1] = child.genes[i];
					child.genes[i] = temp;
				}
			}
		}
		return child;
	}

	public void mutate(float rate)
	{
		if(Random.nextFloat() < rate)
		{
			int chunk = Math.abs(Random.nextInt()%(genes.length/5)*5);
			for(int i = chunk; i < chunk + 5; i++)
			{
				genes[i] = genes[i].mutate();
			}
		}
	}
	
	
	/*public Chromosome crossover(float rate, Chromosome p2) {
		if(rate >= Random.nextFloat())
		{
			Chromosome child = new Chromosome();
			child.genes = new Gene[genes.length];
			for(int i = 0; i < genes.length; i+=5)
			{
				Chromosome from = Random.nextInt()%2==0?p2:this;
				System.arraycopy(from.genes, i, child.genes, i, 5);
			}
			
			return child;
		}
		else return this.copy();
		
	}*/
	
	public Chromosome copy()
	{
		Chromosome child = new Chromosome();
		child.genes = Arrays.copyOf(genes, genes.length);
		child.score = score;
		return child;
	}
	
	public String toString()
	{
		return Float.toString(score);
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return genes.length;
	}

}

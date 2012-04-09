package org.sc2sim.genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.sc2sim.StarcraftWorld;
import org.sc2sim.Unit;
import org.sc2sim.units.Mule;
import org.sc2sim.units.OrbitalCommand;
import org.sc2sim.units.SCV;

public class GeneticStar {

	private float mutationRate = 0.5f;
	private float crossoverRate = 0.9f;
	private int populationSize = 3000;
	private int numberOfGenerations = 150;
	private int numberOfThreads = 5;
	private Chromosome[] population;
	private Chromosome[] temp;
	private StarcraftWorld world;
	private AtomicInteger current;
	
	private class SC2Thread extends Thread
	{
		StarcraftWorld world = new StarcraftWorld();
		
		public SC2Thread(Runnable r) {
			super(r);
		}

		public StarcraftWorld getWorld()
		{
			return world;
		}
	}
	
	class SC2ThreadFactory implements ThreadFactory
	{
		public Thread newThread(Runnable r)
		{
			return new SC2Thread(r);
		}
	}
	
	private class ThreadWorker implements Runnable
	{
		@Override
		public void run() {
			Chromosome  c;
			StarcraftWorld world = ((SC2Thread)Thread.currentThread()).getWorld();
			world.resetWorld();
			int i = current.getAndIncrement();
			c = population[i];
			world.setChromosome(c);
			world.run(360);
			c.setScore(fitness(world));
		}
		
	}
	
	public GeneticStar() throws InterruptedException
	{
		current = new AtomicInteger();
		
		
		temp = new Chromosome[populationSize];
		population = new Chromosome[populationSize];
		world = new StarcraftWorld();
		for(int i = 0; i < populationSize; i++)
		{
			population[i] = new Chromosome(720);
		}
		
		
		for(int i = 0; i < numberOfGenerations; i++)
		{
			scorePopulation();
			System.out.println("Run number "+i+" - Top Fitness: " + population[populationSize-1].getScore());
			crossover();
			mutate();
		}
		
		world.resetWorld();
		world.enableLogging();
		world.setChromosome(population[populationSize-1]);
		world.run(360);
		System.out.println("Top Fitness: " +population[populationSize-1]);
		world.flushLog();
		
	}
	
	
	ThreadWorker worker = new ThreadWorker();
	
	private void scorePopulation() throws InterruptedException
	{
		ExecutorService service =  Executors.newFixedThreadPool(numberOfThreads, new SC2ThreadFactory());
		current.set(0);
		for(int i = 0; i < populationSize-1; i++)
		{
			service.execute(worker);
		}
		service.shutdown();
		service.awaitTermination(5, TimeUnit.MINUTES);
		Arrays.sort(population);
	}
	
	private void mutate()
	{
		for(int i = 0; i < populationSize-1; i++)
		{
			population[i].mutate(mutationRate);
		}
	}
	
	private void crossover()
	{
		int numberInNew = 0;
		temp[populationSize-1] = population[populationSize-1];
		temp[populationSize-2] = (population[populationSize-1].crossover(1.0f));
		while(numberInNew < populationSize-2)
		{

			Chromosome p1 = population[(int)Math.ceil(populationSize/2.0)+(Random.nextInt()%(populationSize/2))];
			//Chromosome p2 = population[(int)Math.ceil(populationSize/2.0)+(Random.nextInt()%(populationSize/2))];
			
			Chromosome child = p1.crossover(crossoverRate);
			temp[numberInNew++] = child;
		}
		population = temp;
		
	}
	
	private float fitness(StarcraftWorld world)
	{
		ArrayList<Unit> units = world.getUnits();
		float ret = 0;
		for(Unit u : units)
		{
			ret+= u.getDPS()*1.5;
			ret+= u.getHP();
			ret+= u.getArmor()*(u.getHP()*0.7);
			if(u instanceof SCV)
			{
				ret+=2;
			}

		}
		return ret;
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new GeneticStar();

	}

}

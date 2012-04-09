# StarCraft 2 Terran Genetic Algorithm

This is a tool for discovering new Terran build orders using a genetic algorithm. 
The program is multithreaded and designed to take advantage of multiple cores for faster processing.


## Requirements

Tested with JDK 6. Should work with JDK 5 and up.

## Configuring

Many of the configuration options are contained in the GeneticStar.java file in src/org/sc2sim/genetic. In reality these should be moved to an external configuration file, but that is not the case yet.
This has the very unfortunate side effect of requiring a recompile whenever an option is changed. Big bummer.

The primary options of interest are:

	mutationRate - This is the percentage chance for a mutation to occur from one generation to the next
	crossoverRate - This is the percentage chance for crossover (breeding) to take place
	populationSize - This is the size of the population 
	numberOfGenerations - This controls how many generations the algorithm will run through before quitting
	numberOfThreads - This controls how many threads run concurrently when scoring the population. Recommend number of cores + 1.

Another thing of interest would be the fitness function ( GeneticStar.fitness() ) . I recommend tweaking it to prioritize different strategies.

## Compiling

With javac in your path, run the following from the root directory:

	mkdir bin
	javac -d bin -sourcepath src src/org/sc2sim/*.java
	javac -d bin -sourcepath src src/org/sc2sim/commands/*.java
	javac -d bin -sourcepath src src/org/sc2sim/genetic/*.java
	javac -d bin -sourcepath src src/org/sc2sim/units/*.java

## Running

Once the files are compiled, you can run the following to start the program:

	cd bin
	java org.sc2sim.genetic.GeneticStar

## Output

Upon completion, the program spits out a file called best.log which contains a log of the actions the top build order took.
The output is full of steps that look like this:

	5.0 - Issued Command: BuildSCV with unit CommandCenter
	Minerals: 0. Gas: 0. Supply: 6/11

This means that at 5.0 game seconds, the program used CommandCenter to build an SCV. After issuing the command, the player is left with 0 minerals, 0 gas, and 6/11 supply.

WINDOWS USERS: Notepad doesn't respect the newlines in the best.log file. You should open it with wordpad instead.

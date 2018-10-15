import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Population {
	
	String target;
	double mutationRate;
	int populationMax;
	List<DNA> dnaList;
	List<DNA> matingPool;
	int generationsNum;
	
	final int stringsDisplayLimit = 50;
	final double perfectMatch = 1.0;
	
	boolean finished;
	String bestMatch = "";
	int maxFitnessScoreElementIndex = 0;
	
	Population(String target, double mutationRate, int populationMax){
		this.target = target;
		this.mutationRate = mutationRate;
		this.populationMax = populationMax;
		dnaList = new ArrayList<DNA>();
		matingPool = new ArrayList<DNA>();
		generationsNum = 0;

		//The boolean values
		this.finished = false;
		
		//Fill our population array
		for(int i = 0; i < populationMax; i++) {	
			DNA newGenome = new DNA(target.length());
			dnaList.add(newGenome);
		}
		
		calculateFitness();
	}
	
	public List<DNA> getPopulation() {
		return dnaList;
	}
	
	public int getGenerations() {
		return generationsNum;
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void calculateFitness() {
		for(int i = 0; i < this.dnaList.size(); i++) {
			this.dnaList.get(i).calculateFitnessValue(target);
		}
	}
	
	public void performNaturalSelection() {
		
		//Make sure the mating pool is cleared
		matingPool = new ArrayList<DNA>();
		
		//Find the max fitness score 
		double maxFitnessScore = 0.0;
		double lowestFitnessScore = 0.0;
		
		for(DNA genome : dnaList) {
			if(genome.fitness > maxFitnessScore) {
				maxFitnessScore = genome.fitness;
			}
		}
		
		for(int i = 0; i < dnaList.size(); i++) {
			
			double normalizedFitness = ((dnaList.get(i).fitness) - lowestFitnessScore)/((maxFitnessScore) - lowestFitnessScore);
			double numOfOccurrencesInSelectionPool = (normalizedFitness <= 0) ? 1 : Math.floor(normalizedFitness * 100);
			
			for(int j = 0; j < numOfOccurrencesInSelectionPool; j++) {
				matingPool.add(dnaList.get(i));
			}
		}		
	}
		
	
	public void generate() {
		
		int sizeOfPool = matingPool.size();
		//Refilling the population
		for(int i = 0; i < dnaList.size(); i++) {
			
			int randomIndex1 = (int) Math.floor(Math.random()*sizeOfPool);
			int randomIndex2 = (int) Math.floor(Math.random()*sizeOfPool);
			DNA firstPartner = matingPool.get(randomIndex1);
			DNA secondPartner = matingPool.get(randomIndex2);
			DNA child = firstPartner.crossover(secondPartner);
			child.mutate(this.mutationRate);
			dnaList.set(i, child);
		}
		calculateFitness();
		this.generationsNum += 1;
	}
	
	// Gets the current most fit member of population and checks whether we
	// found a perfect match so that we can end
	public void evaluate() {
		
		double mostFit = 0.0;
		double currentElementFitness = 0.0;
		int mostFitElementIndex = 0;
		
		for(int i = 0; i < dnaList.size(); i++) {
			currentElementFitness = dnaList.get(i).fitness;
			if( currentElementFitness > mostFit) {
				mostFitElementIndex = i;
				mostFit = currentElementFitness;
			}
		}
		
		this.bestMatch = dnaList.get(mostFitElementIndex).getWholeExpression();
//		this.displayPanel.setBestMatch(this.bestMatch);
		
		if (mostFit >= this.perfectMatch) {
			System.out.println("Finished in " + this.generationsNum + " generation.");
			this.finished = true;
		}
		
	}
	
	
	//For displaying purposes
	public List<String> allExpresions(){
		
		List<String> expressions = new ArrayList<String>();
		
		for(int i = 0; i < this.stringsDisplayLimit; i++) {
			
			String currentExpression = this.dnaList.get(i).getWholeExpression();
			expressions.add(currentExpression);
		}
		
		return expressions;
		
	}
	
	
	
}

import java.util.ArrayList;
import java.util.List;

public class DNA {

	//the genetic sequence (each element's characters)
	private final String ALLOWED_CHARS_DICT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ., ?-";
	int length;
	char[] genome;
	double fitness;
	
	DNA(int leng){
		this.length = leng;
		genome = new char[leng];
		this.fitness = 0;
		
		for(int i = 0; i < leng; i++){
			genome[i] = ALLOWED_CHARS_DICT.charAt((int) Math.floor(Math.random()*ALLOWED_CHARS_DICT.length()));
		}
	}
	
	
	public String getWholeExpression() {
		return new String(this.genome);
	}
	
	//Calculating the fitness value by seeing how many characters match the original string character indices
	public void calculateFitnessValue(String target) {
		double score = 0.0;
		
		for(int i = 0; i < this.genome.length; i++) {
			char currentGene = genome[i];
			if(currentGene == target.charAt(i)) {
				score += 1.0;
			}
		}
		
		this.fitness = score/target.length();
		
		// exponential function makes the evolution happen faster and more efficiently
		// with the POW we increase the chance of the entities with good genes to be selected
		this.fitness = Math.pow(fitness, 4);
	}
	
	public DNA crossover(DNA partner) {
		
		DNA child = new DNA(this.length);
		
		//Given a random split point
		int pointOfSplit = (int) Math.floor(Math.random() * this.length);
		
		//Take part of the partner genes and part of the current ones
		for(int i = 0; i < this.length; i++) {
			if(i > pointOfSplit) { //above the midpoint from the partner	
				child.genome[i] = partner.genome[i];
			}else { //below from the current
				child.genome[i] = this.genome[i];
			}
		}
		return child;
	}
	
	//Flip a character based on the mutation rate chance
	public void mutate(double mutationRate) {
		for(int i = 0; i < this.genome.length; i++) {
			if(Math.random() < mutationRate) {
				this.genome[i] = ALLOWED_CHARS_DICT.charAt((int) Math.floor(Math.random()*ALLOWED_CHARS_DICT.length()));
			}
		}
	}

}
 
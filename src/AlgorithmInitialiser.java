import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlgorithmInitialiser {

	public static void main(String[] args) throws InterruptedException {

		long startTimeMilliSecond = System.currentTimeMillis();

		String target = "To be or not to be that is the question, my dear friend.";
		double mutationRate = 0.01;
		int	populationMax = 500;
		Population population;
		List<DNA> populationArr = new ArrayList<DNA>();

		population = new Population(target,mutationRate, populationMax);

		while(population.finished != true) {

			System.out.println();

			population.calculateFitness();

			population.performNaturalSelection();

			population.generate();

			population.evaluate();

			System.out.println(population.bestMatch);

		}

		long endTimeMilliSecond = System.currentTimeMillis();
		System.out.println("Time Taken: "+(endTimeMilliSecond - startTimeMilliSecond) + " ms");

	}

}

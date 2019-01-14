import java.util.HashMap;
import java.util.Map;

public class Application {

    static String goal = "Genetic Algorithm Example";
    static int size = 100;
    static double mutation = 0.02;
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

    public static void main(String[] args) {
        Population seed, offspring;
        do {
            seed = new Population();
        } while (seed.probe() < 1);
        int generationCount = 0;

        offspring = new Population(seed.breed(seed.createMatingPool(seed.calcFitness())));

        do {
            offspring = new Population(offspring.breed(offspring.createMatingPool(offspring.calcFitness())));
            int max = 0;
            HashMap<String, Integer> offspring_fitness = offspring.calcFitness();
            for (Map.Entry entry : offspring_fitness.entrySet()) {
//                System.out.println(entry.getKey() + ", " + entry.getValue());
                if (max <= (Integer) entry.getValue()) {
                    System.out.println(entry.getKey());
                    max = (Integer) entry.getValue();
                }
            }
            generationCount++;
        } while (!offspring.getPopulation().contains(goal));

        System.out.println("Solution found after " + generationCount + " generations");
    }
}
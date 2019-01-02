import java.util.Map;

public class Application {

    static String goal = "apple";
    static int size = 10;
    static double mutation = 0.01;

    public static void main(String[] args) {
        Population seed, offspring;
        do {
            seed = new Population();
        } while (seed.probe() < 1);
        int generationCount = 0;
        offspring = new Population(seed.breed(seed.createMatingPool(seed.calcFitness())));

//        do {
//            offspring = new Population(seed.breed(seed.createMatingPool(seed.calcFitness())));
//            for (Map.Entry entry : offspring.calcFitness().entrySet()) {
//                System.out.println(entry.getKey() + ", " + entry.getValue());
//            }
//            generationCount++;
//        } while (!offspring.getPopulation().contains(goal));

        System.out.println("Solution found after " + generationCount + " generations");
    }
}
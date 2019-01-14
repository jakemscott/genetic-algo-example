import java.util.*;

public class Population {

    //    private HashMap<String, Integer> fitness = new HashMap<>();
    private ArrayList<String> population;

    public Population() {
        this.population = new ArrayList<>();

        for (int j = 0; j<Application.size; j++){
            this.population.add(this.word());
        }
    }

    public Population(ArrayList<String> population) {
        this.population = population;
    }

    public String word() {
        Random rnd = new Random();
        StringBuilder word = new StringBuilder();
        for(int i = 0; i<Application.goal.length(); i++) {
            word.append(Application.alphabet.charAt(rnd.nextInt(Application.alphabet.length())));
        }
        return word.toString();
    }

    public ArrayList<String> createMatingPool(HashMap<String, Integer> fitness) {
        //METHOD INCOMPLETE, Returned breeding buckets are not equal to initial population, only returning small prime population, should == 100
        HashMap<Float, ArrayList<String>> map = new HashMap<>();
        for (Map.Entry entry : fitness.entrySet()) {
            float value = ((Integer) entry.getValue() / (float) Application.goal.length()) * 100;
//            System.out.println("Word: " + entry.getKey() + " Value: " + value + " Fitness: " + entry.getValue());
            if (map.containsKey(value)) {
                map.get(value).add((String) entry.getKey());
            } else {
                map.put(value, new ArrayList<>());
                map.get(value).add((String) entry.getKey());
            }
        }

        float total_percentage = 0;
        ArrayList<String> bucket = new ArrayList<>();

        for (Map.Entry entry : map.entrySet()) {
            //Add amount to find the remaining
            total_percentage += (float) entry.getKey();
            int multiply = Math.round((float) entry.getKey() / ((ArrayList<String>) entry.getValue()).size());
            for(String word : (ArrayList<String>) entry.getValue()) {
                for (int i = 0; i<multiply; i++) {
                    bucket.add(word);
                }
            }
        }
        float absent_percentage = 100 - bucket.size();

        return bucket;
    }

    public ArrayList<String> breed(ArrayList<String> matingPool) {

        //Combine the genes of each object
        ArrayList<String> nextGen = new ArrayList<>();
        for (int i=0; i<matingPool.size(); i++) {
            String original, partner, child;
            StringBuilder offspring = new StringBuilder();
            do {
                original = matingPool.get(i);
                partner = matingPool.get(new Random().nextInt(matingPool.size()));
            } while (original.equals(partner));

            //Intermingle genes
            for (int j = 0; j<original.length(); j++) {
                if (new Random().nextBoolean()) {
                    offspring.append(original.charAt(j));
                } else {
                    offspring.append(partner.charAt(j));
                }
            }
            //Apply chance of mutation
            if (new Random().nextDouble() <= Application.mutation) {
                child = this.mutate(offspring.toString());
            } else {
                child = offspring.toString();
            }
            nextGen.add(child);
        }
        return nextGen;
    }

    private String mutate(String word) {
        return word.replace(word.charAt(new Random().nextInt(word.length())), Application.alphabet.charAt(new Random().nextInt(Application.alphabet.length())));
    }

    public HashMap<String, Integer> calcFitness() {

        HashMap<String, Integer> fitness = new HashMap<>();

        for (String random : this.population) {
            fitness.put(random, Fitness.calculate(random));
        }
        return fitness;
    }
    public ArrayList<String> getPopulation() {
        return this.population;
    }

    public int probe() {
        int total = 0;
        for (String word : this.population) {
            total += Fitness.calculate(word);
        }
        return total;
    }
}

public class Fitness {
    //Fitness is calculated as a measure of how many letters are present in the correct location
    //Alpha will have a score of five
    public static int calculate(String word) {
        int score = 0;
        for (int i = 0; i<Application.goal.length(); i++) {
            if (Application.goal.charAt(i) == word.charAt(i)) score++;
        }
        return score;
    }
}

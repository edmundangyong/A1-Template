package ca.mcmaster.se2aa4.mazerunner;

public class Factorizer {
    public static String factorize(String solution) {
        String factorized = "";
        int repeat = 0;
        char last = 'F';
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == ' ') {
            } else if (solution.charAt(i) == last) {
                repeat += 1;
            } else if (repeat > 1) {
                factorized +=  "" + repeat + last + " ";
                last = solution.charAt(i);
                repeat = 1;
            } else {
                factorized +=  "" + last + " ";
                last = solution.charAt(i);
                repeat = 1;
            }
        }
        if (repeat > 1) {
            factorized += "" + repeat + last;
        } else {
            factorized += "" + last;
        }
        return factorized;
    }
}
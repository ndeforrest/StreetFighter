import java.util.Scanner;

public class PlayerStrategy extends Strategy {
    private static final int TOTAL_POINTS = 40;

    @Override
    public void playerSetup(Scanner scan, String name) {
        int points = TOTAL_POINTS;
        System.out.println("Welcome, " + name
                + ". Now, you will initialize your strengths, you have " + TOTAL_POINTS
                + " points to put across the board between speed, strength, power, and range. Choose wisely.");

        int speed = setupPrompt(name, points, "speed", scan);
        points -= speed;
        int strength = setupPrompt(name, points, "strength", scan);
        points -= strength;
        int power = setupPrompt(name, points, "power", scan);
        points -= power;
        int range = setupPrompt(name, points, "range", scan);

        System.out.println("Thank you, " + name + "\n");
        super.initialize(name, speed, strength, power, range);
    }

    public int setupPrompt(String name, int points, String statName, Scanner scan) {
        System.out.print("Input " + name + " " + statName + " (" + points + " points remaining): ");
        int input = Integer.parseInt(scan.nextLine());
        while (input > points || input < 0) {
            System.out.print("Input valid " + name + " " + statName + " (" + points + " points remaining): ");
            input = Integer.parseInt(scan.nextLine());
        }
        return input;
    }
}
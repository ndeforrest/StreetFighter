import java.util.Scanner;

public class PlayerStrategy extends Strategy {
    private static final int TOTAL_POINTS = 40;

    Scanner scan = new Scanner(System.in);

    @Override
    public void playerSetup(Scanner scan, String name) {
        int points = TOTAL_POINTS;
        System.out.println("Welcome, " + name
                + ". Now, you will initialize your strengths, you have " + TOTAL_POINTS
                + " points to put across the board between speed, health, power, and range. Choose wisely.");

        int speed = setupPrompt(name, points, "speed", scan);
        points -= speed;
        int health = setupPrompt(name, points, "health", scan);
        points -= health;
        int power = setupPrompt(name, points, "power", scan);
        points -= power;
        int range = setupPrompt(name, points, "range", scan);
        double accuracy = 1.0 - (3 * power / 100);
        System.out.println("Thank you, " + name + "\n");
        super.initialize(name, speed, health, power, range, accuracy);
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

    @Override
    public String getMove(Scanner scan, int inRange) {
        if (inRange == -1) {
            System.out.println(getName() + ", you are out of range.");
        } else if (inRange == 0) {
            System.out.println(getName() + ", you are in kicking range.");
        } else if (inRange == 1) {
            System.out.println(getName() + ", you are in punching range.");
        }
        System.out.print("Do you want to advance(a), retreat(r), punch(p) or kick(k)?: ");
        String response = scan.nextLine();
        while ("arpk".indexOf(response) == -1) {
            System.out.print("Input valid response: ");
            response = scan.nextLine();
        }
        if (response.equals("a")) {
            incrementAdvance();
            return "a";
        } else if (response.equals("r")) {
            incrementRetreat();
            return "r";
        } else if (response.equals("p")) {
            incrementPunch();
            return "p";
        } else if (response.equals("k")) {
            return "k";
        } else {
            System.out.println("error on getMove method");
            return "error";
        }
    }
}
import java.util.Scanner;

public class PlayerStrategy extends Strategy {

    public PlayerStrategy() {
        super();
    }

    @Override
    public void playerSetup(Scanner scan, String name) {
        int points = 30;
        System.out.println("Welcome, " + name + ". Now, you will initialize your strengths, you have 30 points to put across the board between speed, strength, and range. Choose wisely.");

        System.out.print("Input " + name + " speed (" + points + " points remaining): ");
        int speed = Integer.parseInt(scan.nextLine());
        while (speed > points || speed < 0) {
            System.out.print("Input valid " + name + " speed (" + points + " points remaining): ");
            speed = Integer.parseInt(scan.nextLine());
        }
        points -= speed;

        System.out.print("Input " + name + " strength (" + points + " points remaining): ");
        int strength = Integer.parseInt(scan.nextLine());
        while (strength > points || strength < 0) {
            System.out.print("Input valid " + name + " strength (" + points + " points remaining): ");
            strength = Integer.parseInt(scan.nextLine());
        }
        points -= strength;

        System.out.print("Input " + name + " range (" + points + " points remaining): ");
        int range = Integer.parseInt(scan.nextLine());
        while (range > points || range < 0) {
            System.out.print("Input valid " + name + " range (" + points + " points remaining): ");
            range = Integer.parseInt(scan.nextLine());
        }
        System.out.println("Thank you, " + name + "\n");

        super.initialize(name, speed, strength, range);
    }
}
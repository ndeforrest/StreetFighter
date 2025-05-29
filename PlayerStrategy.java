import java.util.Scanner;

public class PlayerStrategy extends Strategy {

    public PlayerStrategy() {
        super();
    }

    @Override
    public void playerSetup(Scanner scan) {
        System.out.print("Input Player 1 name: ");
        String name = scan.nextLine();

        int points = 30;
        System.out.println("Welcome, " + name + ". Now, you will initialize your strengths, you have 30 points to put across the board between speed, strength, and range. Choose wisely.");

        System.out.print("Input Player 1 speed (" + points + " points remaining)");
        int speed = scan.nextInt();
        while (speed > points || speed < 0) {
            System.out.println("Input valid Player 1 speed (" + points + " points remaining): ");
            speed = scan.nextInt();
        }
        points -= speed;

        System.out.print("Input Player 1 strength (" + points + " points remaining): ");
        int strength = scan.nextInt();
        while (strength > points || strength < 0) {
            System.out.println("Input valid Player 1 strength (" + points + " points remaining): ");
            strength = scan.nextInt();
        }
        points -= strength;

        System.out.print("Input Player 1 range (" + points + " points remaining): ");
        int range = scan.nextInt();
        while (range > points || range < 0) {
            System.out.println("Input valid Player 1 range (" + points + " points remaining): ");
            range = scan.nextInt();
        }

        super.initialize(name, speed, strength, range);
    }
}
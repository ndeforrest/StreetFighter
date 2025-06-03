import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PlayerStrategy extends Strategy {

    @Override
    public void playerSetup(Scanner scan, String name) {
        int points = 40;
        System.out.println("Welcome, " + name
                + ". Now, you will initialize your strengths, you have 40 points to put across the board between speed, strength, power, and range. Choose wisely.");

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

        System.out.print("Input " + name + " power (" + points + " points remaining): ");
        int power = Integer.parseInt(scan.nextLine());
        while (power > points || power < 0) {
            System.out.print("Input valid " + name + " power (" + points + " points remaining): ");
            power = Integer.parseInt(scan.nextLine());
        }

        points -= power;

        System.out.print("Input " + name + " range (" + points + " points remaining): ");
        int range = Integer.parseInt(scan.nextLine());
        while (range > points || range < 0) {
            System.out.print("Input valid " + name + " range (" + points + " points remaining): ");
            range = Integer.parseInt(scan.nextLine());
        }
        System.out.println("Thank you, " + name + "\n");

        super.initialize(name, speed, strength, power, range);
    }

    @Override
    public void storeInfo() {
        try {
            boolean append = new java.io.File("Strategies.txt").exists()
                    && new java.io.File("Strategies.txt").length() > 0;
            FileOutputStream fileOut = new FileOutputStream("Strategies.txt", true); // append mode
            ObjectOutputStream out;
            if (append) {
                // Custom ObjectOutputStream to avoid writing a header again
                out = new ObjectOutputStream(fileOut) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            } else {
                out = new ObjectOutputStream(fileOut);
            }
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
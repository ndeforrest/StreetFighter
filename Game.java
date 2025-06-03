import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static ArrayList<Strategy> strategies;
    Strategy player1, player2;
    int player1Damage = 2;
    int player1Health = 10;
    int player1Range = 2;
    int player2Damage = 2;
    int player2Health = 10;
    int player2Range = 2;

    int range = 5;
    boolean turn;

    /*
     * speed: every few turns, get another kick
     * strength: amount of health
     * power: damage per kick
     * range: how far out of range to kick
     */

    public static void getStrategies() {
        strategies = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream("Strategies.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Strategy s = (Strategy) in.readObject();
                    strategies.add(s);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    public Strategy pickStrategy(Scanner scan) {
        System.out.println("Pick one of these charachters: ");
        for (int i = 0; i < strategies.size(); i++) {
            Strategy s = strategies.get(i);
            System.out.println(i + ": " + s.getName() + " (Speed: " + s.getSpeed() + ", Strength: " + s.getStrength()
                    + ", Power: " + s.getPower() + ", Range: " + s.getRange() + ")");
        }
        System.out.print("Pick a strategy number: ");
        return strategies.get(Integer.parseInt(scan.nextLine()));
    }

    public void setup(Scanner scan) {

        System.out.print("Number of in-person players (0, 1, or 2): ");
        int num = Integer.parseInt(scan.nextLine());
        if (num <= 1) {
            getStrategies();
            if (num == 0) {
                player1 = pickStrategy(scan);
                System.out.print("Input user's name: ");
                player1.playerSetup(scan, scan.nextLine());

                player2 = pickStrategy(scan);
                System.out.print("Input user's name: ");
                player2.playerSetup(scan, scan.nextLine());

            } else if (num == 1) {
                player1 = new PlayerStrategy();
                System.out.print("Input Player 1 (in-person) name: ");
                player1.playerSetup(scan, scan.nextLine());

                player2 = pickStrategy(scan);
                System.out.print("Input user's name: ");
                player2.playerSetup(scan, scan.nextLine());
            }
        } else {
            player1 = new PlayerStrategy();
            System.out.print("Input Player 1 name: ");
            player1.playerSetup(scan, scan.nextLine());

            player2 = new PlayerStrategy();
            System.out.print("Input Player 2 name: ");
            player2.playerSetup(scan, scan.nextLine());
        }

        if (player1.getSpeed() > player2.getSpeed()) {

        } else if (player2.getSpeed() > player1.getSpeed()) {

        }
        if (player1.getRange() > player2.getRange()) {
            player1Range++;
        }
        if (player1.getRange() > player2.getRange() + 4) {
            player1Range++;
        }
        if (player2.getRange() > player1.getRange()) {
            player2Range++;
        }
        if (player2.getRange() > player1.getRange() + 4) {
            player2Range++;
        }

    }

    public void play(Scanner scan) {
        printStats();
        while (player1Health > 0 && player2Health > 0) {

        }
        end(scan);
    }

    public void printStats() {
        System.out.print(player1.getName() + "\t\t\t" + player2.getName() + "\n");
        for (int i = 0; i < player1Health; i++) {
            System.out.print("♥ ");
        }
        System.out.print("\t");
        for (int i = 0; i < player2Health; i++) {
            System.out.print("♥ ");
        }
        System.out.print("\n\t웃");
        for (int i = 0; i < range; i++) {
            System.out.print(",_");
        }
        System.out.println(",웃");
    }

    public Strategy getTurn() {
        if (turn) {
            return player1;
        }
        return player2;
    }

    public void end(Scanner scan) {
        System.out.println(player1.getName() + ", do you want to save your data as an AI player? (yes/no)");
        if (scan.nextLine().equalsIgnoreCase("yes")) {
            player1.storeInfo();
        }

        System.out.println(player2.getName()
                + ", do you want to save your data as an AI player? (yes/no) (Select yes if AI player)");
        if (scan.nextLine().equalsIgnoreCase("yes")) {
            player2.storeInfo();
        }
    }
}
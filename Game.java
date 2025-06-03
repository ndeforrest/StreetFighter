import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static ArrayList<Strategy> strategies;
    private Strategy player1, player2;

    private int player1Damage = 2;
    private int player1Health = 10;
    private int player1Range = 2;
    private int player2Damage = 2;
    private int player2Health = 10;
    private int player2Range = 2;

    private int range = 5;
    private boolean turn;

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }
    }

    public static Strategy pickStrategy(Scanner scan) {
        System.out.println("Pick one of these characters: ");
        for (int i = 0; i < strategies.size(); i++) {
            Strategy s = strategies.get(i);
            System.out.println(i + ": " + s.getName() + " (Speed: " + s.getSpeed() + ", Strength: " + s.getStrength()
                    + ", Power: " + s.getPower() + ", Range: " + s.getRange() + ")");
        }
        System.out.print("Pick a strategy number: ");
        return strategies.get(Integer.parseInt(scan.nextLine()));
    }

    public static void updateStrategyList(Scanner scan, Strategy player1, Strategy player2) {
        boolean addPlayer1 = player1 instanceof PlayerStrategy;
        boolean addPlayer2 = player2 instanceof PlayerStrategy;

        for (int i = 0; i < strategies.size(); i++) {
            if (strategies.get(i).getName().equals(player1.getName())) {
                if (player1 instanceof PlayerStrategy) {
                    addPlayer1 = false;
                } else if (player1 instanceof AIStrategy) {
                    strategies.set(i, player1);
                }
            } else if (strategies.get(i).getName().equals(player2.getName())) {
                if (player2 instanceof PlayerStrategy) {
                    addPlayer2 = false;
                } else if (player2 instanceof AIStrategy) {
                    strategies.set(i, player2);
                }
            }
        }
        if (addPlayer1) {
            System.out.print("Do you want to upload your data as an AI?: ");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
                strategies.add(player1);
            }
        }
        if (addPlayer2) {
            System.out.print("Do you want to upload your data as an AI?: ");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
                strategies.add(player2);
            }
        }
    }

    public static void uploadStrategies() {
        try (FileOutputStream fileOut = new FileOutputStream("Strategies.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            for (Strategy s : strategies) {
                out.writeObject(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        /*
         * if (player1.getSpeed() > player2.getSpeed()) {
         * 
         * } else if (player2.getSpeed() > player1.getSpeed()) {
         * 
         * }
         * if (player1.getRange() > player2.getRange()) {
         * player1Range++;
         * }
         * if (player1.getRange() > player2.getRange() + 4) {
         * player1Range++;
         * }
         * if (player2.getRange() > player1.getRange()) {
         * player2Range++;
         * }
         * if (player2.getRange() > player1.getRange() + 4) {
         * player2Range++;
         * }
         */

    }

    public void play(Scanner scan) {
        printStats();
        while (player1Health > 0 && player2Health > 0) {

        }
        updateStrategyList(scan, player1, player2);
        uploadStrategies();
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

}
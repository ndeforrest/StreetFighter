import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static ArrayList<Strategy> strategies;
    private Strategy player1, player2;

    private int player1Damage = 4;
    private int player2Damage = 4;
    private int player1Health = 10;
    private int player2Health = 10;
    private int player1Range = 1;
    private int player2Range = 1;
    private double player2MoveAdvantage = .05;
    private double player1MoveAdvantage = .05;

    private int range = 5;
    private boolean turn;

    /*
     * speed: every few turns, get another kick (by random, as a percent chance of
     * an extra turn);
     * health: amount of health
     * power: max damage per kick (do random)
     * range: how far out of range to punch (kick range = 2*range)
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
            System.out.println(i + ": " + s.getName() + " (Speed: " + s.getSpeed() + ", Health: " + s.getHealth()
                    + ", Power: " + s.getPower() + ", Range: " + s.getRange() + ") (" + s.getWins() + "wins, "
                    + s.getLosses() + " losses)");
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

        if (player1.getSpeed() > player2.getSpeed()) {
            for (int i = 0; i < player1.getSpeed() - player2.getSpeed(); i += 2) {
                if (player1MoveAdvantage < .5) {
                    player1MoveAdvantage += 0.1;
                }
            }
            System.out.println(player1.getName() + " has a speed advantage. (Will get extra turns)");
        } else if (player2.getSpeed() > player1.getSpeed()) {
            for (int i = 0; i < player2.getSpeed() - player1.getSpeed(); i += 2) {
                if (player2MoveAdvantage < .5) {
                    player2MoveAdvantage += 0.1;
                }
            }
            System.out.println(player2.getName() + " has a speed advantage. (Will get extra turns)");
        }
        if (player1.getRange() > player2.getRange() + 2) {
            player1Range++;
            if (player1.getRange() > player2.getRange() + 7) {
                player1Range++;
            }
            System.out.println(player1.getName() + " has a range advantage. Their range is now " + player1Range);
        } else if (player2.getRange() > player1.getRange() + 2) {
            player2Range++;
            if (player2.getRange() > player1.getRange() + 7) {
                player2Range++;
            }
            System.out.println(player2.getName() + " has a range advantage. Their range is now " + player2Range);
        }
        if (player1.getHealth() > player2.getHealth() + 2) {
            player1Health++;
            if (player1.getHealth() > player2.getHealth() + 7) {
                player1Health++;
            }
            System.out.println(player1.getName() + " has a health advantage. Their range is now " + player1Health);
        } else if (player2.getHealth() > player1.getHealth() + 2) {
            player2Health++;
            if (player2.getHealth() > player1.getHealth() + 7) {
                player2Health++;
            }
            System.out.println(player2.getName() + " has a health advantage. Their range is now " + player2Health);
        }
        if (player1.getPower() > player2.getPower() + 2) {
            player1Damage++;
            if (player2.getPower() > player1.getPower() + 7) {
                player1Damage++;
            }
            System.out.println(
                    player1.getName() + " has a power advantage. Their max damage is now " + 2 * player1Damage);
        } else if (player2.getPower() > player1.getPower() + 2) {
            player2Damage++;
            if (player1.getPower() > player2.getPower() + 7) {
                player2Damage++;
            }
            System.out.println(
                    player2.getName() + " has a power advantage. Their max damage is now " + 2 * player2Damage);
        }
    }

    public void play(Scanner scan) {
        while (player1Health > 0 && player2Health > 0) {
            System.out.println("-----------------------------\n" + getTurn().getName() + "'s turn:");
            printStats();
            int inRange = -1;// out of range
            if (getTurn().equals(player1)) {
                if (range <= player1Range) {
                    inRange = 1;// in punch range
                } else if (range <= player1Range * 2) {
                    inRange = 0;// in kick range
                }
            } else if (getTurn().equals(player2)) {
                if (range <= player2Range) {
                    inRange = 1;
                } else if (range <= player2Range * 2) {
                    inRange = 0;
                }
            }
            String response = getTurn().getMove(scan, inRange);
            if (response.equals("a")) {
                range--;
            } else if (response.equals("r")) {
                range++;
            } else if (response.equals("p")) {
                // PUNCH
                if (getTurn().punchLand()) {
                    // DAMAGE
                    int punchDamage;
                    if (getTurn().equals(player1)) {
                        punchDamage = (int) (Math.random() * player1Damage / 2 + 1);
                        player2Health -= punchDamage;
                    } else if (getTurn().equals(player2)) {
                        punchDamage = (int) (Math.random() * player2Damage / 2 + 1);
                        player1Health -= punchDamage;
                    } else {
                        punchDamage = -1;
                        System.out.println("ERROR @ punching in play() method");
                    }
                    System.out.println(getTurn().getName() + " landed the punch, and dealt " + punchDamage + "damage");
                } else {
                    System.out.println(getTurn().getName() + " missed the punch");
                }
            } else if (response.equals("k")) {
                // KICK
                if (getTurn().kickLand()) {
                    // DAMAGE
                    int kickDamage;
                    if (getTurn().equals(player1)) {
                        kickDamage = (int) (Math.random() * player1Damage + 1);
                        player2Health -= kickDamage;
                    } else if (getTurn().equals(player2)) {
                        kickDamage = (int) (Math.random() * player2Damage + 1);
                        player1Health -= kickDamage;
                    } else {
                        kickDamage = -1;
                        System.out.println("ERROR @ punching in play() method");
                    }
                    System.out.println(getTurn().getName() + " landed the kick, and dealt " + kickDamage + "damage");
                } else {
                    System.out.println(getTurn().getName() + " missed the kick");
                }
            }

            if (getTurn().equals(player1)) {
                if (Math.random() < player1MoveAdvantage) {
                    System.out.println(player1.getName() + " gets an extra turn!");
                } else {
                    System.out.println("Switching turns");
                    switchTurns();
                }
            } else if (getTurn().equals(player2)) {
                if (Math.random() < player2MoveAdvantage) {
                    System.out.println(player2.getName() + " gets an extra turn!");
                } else {
                    System.out.println("Switching turns");
                    switchTurns();
                }
            }
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

    public void switchTurns() {
        turn = !turn;
    }

    public void end(Scanner scan) {
        if (player1Health <= 0) {
            System.out.println(player1.getName() + " looses!");
            System.out.println("Great work, " + player2.getName() + "! Here's your trophy:");
        } else if (player2Health <= 0) {
            System.out.println(player2.getName() + " looses!");
            System.out.println("Great work, " + player1.getName() + "! Here's your trophy:");
        }
        printTrophy();
        updateStrategyList(scan, player1, player2);
        uploadStrategies();
    }

    public static void printTrophy() {
        System.out.println(
                "\\ \\  / /  .---------,  | |     | |       \\ \\                  / /  | |  |   \\     | |        __________                    /|");
        System.out.println(
                " \\ \\/ /   | ,-----, |  | |     | |        \\ \\                / /   | |  | |\\ \\    | |        \\________/      |      |     / |");
        System.out.println(
                "  \\  /    | |     | |  | |     | |         \\ \\              / /    | |  | | \\ \\   | |         \\______/     --|------|--     |");
        System.out.println(
                "  |  |    | |     | |  | |     | |          \\ \\    ____    / /     | |  | |  \\ \\  | |           |  |         |      |       |");
        System.out.println(
                "  |  |    | |     | |  | |     | |           \\ \\  / /\\ \\  / /      | |  | |   \\ \\ | |           |  |       --|------|--     |");
        System.out.println(
                "  |  |    | |_____| |  | |_____| |            \\ \\/ /  \\ \\/ /       | |  | |    \\ \\| |           |  |         |      |       |");
        System.out.println(
                "  |  |    |_________|  |_________|             \\__/     \\_/        | |  | |     \\___|          /____\\                    -------");

    }
}
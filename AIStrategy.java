import java.util.Scanner;

public class AIStrategy extends Strategy {
    private int wins;
    private int losses;
    private transient String tempName;

    @Override
    public void playerSetup(Scanner scan, String name) {
        tempName = super.getName() + "(" + name + ")";
    }

    @Override
    public String getName() {
        return tempName;
    }

    @Override
    public String getMove(Scanner scan, int inRange) {
        if (inRange == 1) {
            if (Math.random() < getAdvanceChance()) {
                System.out.println(getName() + " is advancing 1");
                return "a";
            } else if (Math.random() < getAttackChance()) {
                if (Math.random() < getPunchChance()) {
                    System.out.println(getName() + "is punching");
                    return "p";
                } else {
                    System.out.println(getName() + "is kicking");
                    return "k";
                }

            } else {
                System.out.println(getName() + " is retreating 1");
                return "r";
            }
        } else if (inRange == 0) {
            if (Math.random() < getAdvanceChance()) {
                System.out.println(getName() + " is advancing 1");
                return "a";
            } else if (Math.random() < getAttackChance()) {
                System.out.println(getName() + "is punching");
                return "p";
            } else {
                System.out.println(getName() + " is retreating 1");
                return "r";
            }
        } else if (inRange == -1) {
            if (Math.random() < getAdvanceChance()) {
                System.out.println(getName() + " is advancing 1");
                return "a";
            } else {
                System.out.println(getName() + " is retreating 1");
                return "r";
            }
        } else {
            System.out.println("Error, AI kicked when out of all range");
            return "error";
        }
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLoss() {
        losses++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

}
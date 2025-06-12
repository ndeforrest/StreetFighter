import java.io.*;
import java.util.Scanner;

public class Strategy implements Serializable {

    private String name;
    private int speed;
    private int health;
    private int power;
    private int range;
    private double kickAccuracy;
    private double punchAccuracy;
    private double retreatChance;
    private double advanceChance;
    private double attackChance;
    private double moves;
    private double punchChance;
    private double attackMoves;

    public void initialize(String name, int speed, int health, int power, int range, double accuracy) {
        this.name = name;
        this.speed = speed;
        this.health = health;
        this.power = power;
        this.range = range;
        kickAccuracy = accuracy;
        punchAccuracy = accuracy + 0.15;

    }

    public void playerSetup(Scanner scan, String name) {
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getPower() {
        return power;
    }

    public int getRange() {
        return range;
    }

    public boolean punchLand() {
        // punches are often more accurate, by about 15%
        return (punchAccuracy > Math.random());
    }

    public boolean kickLand() {
        return (kickAccuracy > Math.random());
    }

    public String getMove(Scanner scan, int inRange) {
        System.out.println("error calling getMove() method");
        return "error";
    }

    public void incrementAdvance() {
        advanceChance = (advanceChance * moves + 1) / (moves + 1);
        moves++;
    }

    public void incrementRetreat() {
        retreatChance = (retreatChance * moves + 1) / (moves + 1);
        moves++;
    }

    public void incrementAttack() {
        attackChance = (attackChance * moves + 1) / (moves + 1);
        moves++;
    }

    public double getAdvanceChance() {
        return advanceChance;
    }

    public double getRetreatChance() {
        return retreatChance;
    }

    public double getAttackChance() {
        return attackChance;
    }

    public void incrementPunch() {
        punchChance = (punchChance * attackMoves + 1) / (attackMoves + 1);
        attackMoves++;
    }

    public double getPunchChance() {
        return punchChance;
    }

    public void incrementWins() {
    }

    public void incrementLoss() {
    }

    public int getWins() {
        return -1;
    }

    public int getLosses() {
        return -1;
    }
}
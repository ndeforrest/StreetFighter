import java.util.Scanner;

public class Strategy {
    private String name;
    private int speed;
    private int strength;
    private int range;

    public Strategy() {
    }

    public void initialize(String name, int speed, int strength, int range) {
        this.name = name;
        this.speed = speed;
        this.strength = strength;
        this.range = range;
    }

    public void playerSetup(Scanner scan, String name) {
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getRange() {
        return range;
    }
}
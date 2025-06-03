import java.io.*;
import java.util.Scanner;

public class Strategy implements Serializable {
    private static final int TOTAL_POINTS = 40;

    private String name;
    private int speed;
    private int strength;
    private int power;
    private int range;

    public void initialize(String name, int speed, int strength, int power, int range) {
        this.name = name;
        this.speed = speed;
        this.strength = strength;
        this.power = power;
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

    public int getPower() {
        return power;
    }

    public int getRange() {
        return range;
    }
}

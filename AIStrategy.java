import java.util.Scanner;

public class AIStrategy extends Strategy {
    private int wins;
    private int losses;
    private double retreatChance;
    private double attackChance;
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
    public void storeInfo() {

    }

}
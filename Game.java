import java.util.Scanner;

public class Game{
    Strategy player1, player2;
    int speedAdvantage;
    int strengthAdvantage;
    int rangeAdvantage;

    public void setup(Scanner inputScanner){
        Strategy player1 = new PlayerStrategy();
        /* System.out.println("How many players? 1 or 2");
        if (inputScanner.nextInt() == 1) {
            Strategy player2 = new AIStrategy();
        } */
        //else {
            Strategy player2 = new PlayerStrategy();
        //}

        System.out.print("Input Player 1 name: ");
        String name1 = inputScanner.nextLine();
        player1.playerSetup(inputScanner, name1);

        System.out.print("Input Player 2 name: ");
        String name2 = inputScanner.nextLine();
        player2.playerSetup(inputScanner, name2);

    }
    
    public void play(){
        speedAdvantage = player1.getSpeed() - player2.getSpeed();
        strengthAdvantage = player1.getStrength() - player2.getStrength();
        rangeAdvantage = player1.getRange() - player2.getRange();
    }
}
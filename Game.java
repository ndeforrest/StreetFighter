import java.util.Scanner;

public class Game{
    Strategy player1, player2;
    public void setup(Scanner inputScanner){
        /* For AI game
        System.out.println("How many players? 1 or 2");
        if (inputScanner.nextInt() == 1) {
            Strategy Player1 = new PlayerStrategy();
            Strategy Player2 = new AIStrategy();
            Player1.playerSetup(inputScanner);
            Player2.playerSetup(inputScanner);
            return;
        }
        */
        Strategy player1 = new PlayerStrategy();
        Strategy player2 = new PlayerStrategy();
        player1.playerSetup(inputScanner);
        player2.playerSetup(inputScanner);

    }
    
    public void play(){

    }
}
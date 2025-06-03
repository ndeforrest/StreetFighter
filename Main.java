import java.util.Scanner;

public class Main {
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("------------------------------------\nWelcome to the Kung Jung Mu Sul!");
        Game game = new Game();
        game.setup(inputScanner);
        game.play(inputScanner);
    }
}

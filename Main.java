import java.util.Scanner;
public class Main {
    static Scanner inputScanner = new Scanner(System.in);
    public static void main(String[] args) {
        Game game = new Game();
        game.setup(inputScanner);
        game.play();
    }
}

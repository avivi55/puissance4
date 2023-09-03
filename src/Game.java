import java.util.Scanner;

public class Game {
    final static String token = "●";
    final static int player1color = 27;
    final static int player2color = 196;

    public static void main(String[] args) {
        Board board = new Board(7, 6);
        boolean game = true;

        Scanner scanner = new Scanner(System.in);

        boolean isPlayer1 = true;

        while (game){
            System.out.println("Player : " + new ColoredText(token, isPlayer1 ? player1color : player2color));
            System.out.println("Enter column");
            int column = scanner.nextInt();

            String token = isPlayer1 ? "0" : "1";

            if (board.placeToken(column, token)){
                continue;
            }

            System.out.println(board);

            if (board.isTokenWinning(token)){
                System.out.println("YOU WON!");
                return;
            }

            isPlayer1 = !isPlayer1;
        }

        System.out.println("DRAW!");
    }
}

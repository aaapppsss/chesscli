import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    public static void print(String text){
        System.out.print(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static void printBoard(Board board) {
        System.out.print("  a  b  c  d  e  f  g  h");
        System.out.println();
        for (var i = 8; i >0; i--) {
            System.out.print(i + " ");
            for (var j = 0; j < 8; j++) {
                String piece = board.pieces[i][j] != null ? board.pieces[i][j] : "..";

                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }

    public static String readInput(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(in);
        String userInput = "";
        userInput = sc.nextLine().toLowerCase().trim();
        return userInput;
    }
}

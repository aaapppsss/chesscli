public class ChessGame {

    private void run() {
        Board board = new Board();
        MoveParser moveParser = new MoveParser();
        Color currColor = Color.WHITE;

        UI.println("Welcome to the CLI Chess app!\n");
        UI.println("Instructions: \n"
                + "Each piece is represented by two characters. The first character\n"
                + "denotes the piece type and the second character denotes the color.\n"
                + "e.g. rb represents a black rook.\n"
                + "\n"
                + "Input the starting position and ending position to move a piece:\n"
                + "e.g. e4 e6\n"
                + "\n"
                + "Key for piece types:\n"
                + "p: pawn, n: knight, b: bishop, r: rook, q: queen, k: king\n");

        UI.printBoard(board);

        while (true) {
            UI.print(currColor.toString() + " turn:");
            UI.println("");

            String userInput = UI.readInput(System.in, System.out);
            try {
                MoveCommand cmd = moveParser.parseMove(userInput, board);
                boolean wasSuccessfullyMoved = cmd.executeMove(board, currColor);
                if (wasSuccessfullyMoved == true) {
                    currColor = (currColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
                    UI.printBoard(board);
                    UI.println("");
                }
            } catch (Exception e) {
                UI.println("Please follow the input format eg e4 e6");
            }
        }
    }

    public static void main(String[] args) {
        new ChessGame().run();
    }
}

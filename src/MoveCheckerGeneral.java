public class MoveCheckerGeneral {
    int pos1x;
    int pos1y;
    int pos2x;
    int pos2y;

    public MoveCheckerGeneral(int pos1x, int pos1y, int pos2x, int pos2y) {
        this.pos1x = pos1x;
        this.pos1y = pos1y;
        this.pos2x = pos2x;
        this.pos2y = pos2y;
    }

    public boolean checkMoveGeneralRules(Board board, Color currColor) {
        if (checkIfPieceExists(board) == false) {
            return false;
        }
        if (checkColorValidity(board, currColor) == false) {
            return false;
        }
        if (checkIfNewPosWithinBoard() == false) {
            return false;
        }
        if (checkIfCapturingEnemyPiece(board, currColor) == false) {
            return false;
        }
        return true;
    }

    public boolean checkIfPieceExists(Board board) {
        String movingPiece = board.pieces[pos1y][pos1x];
        if(movingPiece == null) {
            UI.println("No piece at that starting position!");
            return false;
        }
        return true;
    }

    public boolean checkColorValidity(Board board, Color currColor) {
        String movingPiece = board.pieces[pos1y][pos1x];
        if (currColor.toString().equals(getColorOfPiece(movingPiece)) == true) {
            return true;
        }
        UI.println("You can't move another player's pieces!");
        return false;
    }

    public boolean checkIfNewPosWithinBoard() {
        if(pos2y > 8 || pos2y < 1 || pos2x > 7) {
            UI.println("New position not within board!");
            return false;
        }
        return true;
    }

    public boolean checkIfCapturingEnemyPiece(Board board, Color currColor) {
        String eatenPiece = board.pieces[pos2y][pos2x];
        if(eatenPiece != null) {//eating a piece
            if(currColor.toString().equals(getColorOfPiece(eatenPiece)) == true) {//ate own piece
                UI.println("You cannot capture your own piece!");
                return false;
            }
        }
        return true;
    }

    private String getColorOfPiece(String piece) {
        return Character.toString(piece.charAt(1));
    }
}

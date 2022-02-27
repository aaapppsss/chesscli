public class MoveCheckerByPiece {
    int pos1x;
    int pos1y;
    int pos2x;
    int pos2y;

    public MoveCheckerByPiece(int pos1x, int pos1y, int pos2x, int pos2y) {
        this.pos1x = pos1x;
        this.pos1y = pos1y;
        this.pos2x = pos2x;
        this.pos2y = pos2y;
    }

    public boolean checkMoveValidity(Board board, Color currColor) {
        String pieceType = getPieceType(board);
        int forwardSquares = parseForwardSquares(currColor);
        int backwardSquares = parseBackwardSquares(currColor);
        int xSquares = Math.abs(pos2x - pos1x);
        int ySquares = Math.abs(pos2y - pos1y);

        //check clear path if not knight
        if (pieceType.equals("n") == false && checkClearPath(board, xSquares, ySquares) == false) {
            UI.println("This type of piece cannot jump over other pieces!");
            return false;
        }

        //check validity according to piece type. no checks required for queen
        switch (pieceType) {
        case "p":
            if (checkPawnMoveValidity(backwardSquares, forwardSquares, xSquares, currColor, board) == false) {
                return false;
            }
            break;
        case "b":
            if (checkBishopMoveValidity(xSquares, ySquares) == false) {
                return false;
            }
            break;
        case "r":
            if (checkRookMoveValidity(xSquares, ySquares) == false) {
                return false;
            }
            break;
        case "k":
            if (checkKingMoveValidity(xSquares, ySquares) == false) {
                return false;
            }
        case "n":
            if(checkKnightMoveValidity(xSquares, ySquares) == false) {
                return false;
            }
        default:
        }

        return true;
    }

    private boolean checkPawnMoveValidity(int backwardSquares, int forwardSquares, int xSquares,
                                          Color currColor, Board board) {
        int pawnStartY = currColor == Color.WHITE ? 2 : 7;

        //cannot go backwards
        if (backwardSquares > 0) {
            UI.println("Pawns cannot move backwards!");
            return false;
        }

        //can move diagonally if capturing a piece - otherwise x coordinate should remain the same
        if (xSquares > 0) { //moved left or right
            boolean movedLRByOne = xSquares == 1 ? true : false;
            boolean movedForwardByOne = forwardSquares == 1 ? true : false;
            boolean capturedPieceAvailable = board.pieces[pos2y][pos2x] != null ? true : false;
            if (!(movedLRByOne && movedForwardByOne && capturedPieceAvailable)) {
                UI.println("Pawns cannot move in this manner!");
                return false; //x coordinate changed illegally
            }
        } else { //x coordinate did not change
            if (pos1y == pawnStartY) { //if not moved yet, can move two steps forward
                if (forwardSquares > 2) {
                    UI.println("This pawn can only move forward 2 squares!");
                    return false;
                }
            } else if (forwardSquares > 1) { //otherwise, can only move one square forward at a time
                UI.println("This pawn can only move forward 1 square!");
                return false;
            }
        }
        return true;
    }

    private boolean checkKnightMoveValidity(int xSquares, int ySquares) {
        boolean isCaseX2Y1 = (xSquares == 2 && ySquares == 1) ? true : false;
        boolean isCaseX1Y2 = (xSquares == 1 && ySquares == 2) ? true : false;
        if (!(isCaseX2Y1 || isCaseX1Y2)) {
            UI.println("Knights cannot move in this manner!");
            return false;
        }
        return true;
    }

    private boolean checkBishopMoveValidity(int xSquares, int ySquares) {
        //check that moved diagonally
        if (ySquares != xSquares) {
            UI.println("Bishops cannot move in this manner!");
            return false;
        }
        return true;
    }

    private boolean checkRookMoveValidity(int xSquares, int ySquares) {
        if (xSquares > 0 && ySquares > 0) {
            UI.println("Rooks cannot move in this manner!");
            return false;
        }
        return true;
    }

    private boolean checkKingMoveValidity(int xSquares, int ySquares) {
        if (xSquares > 1 || ySquares > 1) {
            UI.println("Kings can only move 1 square at a time!");
            return false;
        }
        return true;
    }

    private String getPieceType(Board board) {
        String movingPiece = board.pieces[pos1y][pos1x];
        return Character.toString(movingPiece.charAt(0));
    }

    private int parseForwardSquares(Color currColor) {
        if (currColor == Color.WHITE) {
            return Math.max(pos2y - pos1y, 0);
        } else if (currColor == Color.BLACK) {
            return Math.max(pos1y - pos2y, 0);
        }
        return 0;
    }

    private int parseBackwardSquares(Color currColor) {
        if (currColor == Color.WHITE) {
            return Math.max(pos1y - pos2y, 0);
        } else if (currColor == Color.BLACK) {
            return Math.max(pos2y - pos1y, 0);
        }
        return 0;
    }

    private boolean checkClearPath(Board board, int xSquares, int ySquares) {
        if (xSquares > 0 && ySquares > 0) { //check diagonal path
            int startingYPos = pos1y < pos2y ? pos1y : pos2y;
            boolean isGoingRight = pos2x - pos1x > 0 ? true : false;
            for (var i = 1; i < xSquares; i++) {
                int xPos = isGoingRight ? pos1x + i : pos1x - i;
                if (board.pieces[startingYPos + i][xPos] != null) {
                    return false;
                }
            }
        } else if (xSquares > 0) { //check horizontal path
            int startingXPos = pos1x < pos2x ? pos1x : pos2x;
            for (var i = 1; i < xSquares; i++) {
                if (board.pieces[pos1y][startingXPos + i] != null) {
                    return false;
                }
            }
        } else if (ySquares > 0) { //check vertical path
            int startingYPos = pos1y < pos2y ? pos1y : pos2y;
            for (var i = 1; i < ySquares; i++) {
                if (board.pieces[startingYPos + i][pos1x] != null) {
                    return false;
                }
            }
        }
        return true;
    }
}

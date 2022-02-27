public class MoveCommand {
    int pos1x;
    int pos1y;
    int pos2x;
    int pos2y;
    MoveCheckerGeneral moveCheckerGeneral;
    MoveCheckerByPiece moveCheckerByPiece;

    public MoveCommand(int pos1x, int pos1y, int pos2x, int pos2y) {
        this.pos1x = pos1x;
        this.pos1y = pos1y;
        this.pos2x = pos2x;
        this.pos2y = pos2y;
        moveCheckerGeneral = new MoveCheckerGeneral(pos1x, pos1y, pos2x, pos2y);
        moveCheckerByPiece = new MoveCheckerByPiece(pos1x, pos1y, pos2x, pos2y);
    }


    //returns true if move was executed successfully
    public boolean executeMove(Board board, Color currColor) {
        if(moveCheckerGeneral.checkMoveGeneralRules(board, currColor) == false) {
            return false;
        }
        if (moveCheckerByPiece.checkMoveValidity(board, currColor) == false) {
            return false;
        }

        board.pieces[pos2y][pos2x] = board.pieces[pos1y][pos1x];
        board.pieces[pos1y][pos1x] = null;
        return true;
    }
}

import java.awt.SystemTray;
import java.lang.Character;

public class MoveParser {
    public MoveCommand parseMove(String input, Board board){
        String[] positions = input.split(" ");
        String pos1 = positions[0].trim();
        String pos2 = positions[1].trim();

        int pos1y = Character.getNumericValue(pos1.charAt(1));
        int pos1x = Character.getNumericValue(pos1.charAt(0)) - 10;

        int pos2y = Character.getNumericValue(pos2.charAt(1)) ;
        int pos2x = Character.getNumericValue(pos2.charAt(0)) - 10;

        return new MoveCommand(pos1x, pos1y, pos2x, pos2y);
    }
}

import java.util.HashMap;

public class Board {
    public String[][] pieces = new String[9][8];

    public Board() {
        //setup black
        pieces[8][0] = "rb";
        pieces[8][1] = "nb";
        pieces[8][2] = "bb";
        pieces[8][3] = "qb";
        pieces[8][4] = "kb";
        pieces[8][5] = "bb";
        pieces[8][6] = "nb";
        pieces[8][7] = "rb";
        for(var i = 0; i < 8; i++) {
            pieces[7][i] = "pb";
        }

        //setup white
        pieces[1][0] = "rw";
        pieces[1][1] = "nw";
        pieces[1][2] = "bw";
        pieces[1][3] = "qw";
        pieces[1][4] = "kw";
        pieces[1][5] = "bw";
        pieces[1][6] = "nw";
        pieces[1][7] = "rw";
        for(var i = 0; i < 8; i++) {
            pieces[2][i] = "pw";
        }
    }
}

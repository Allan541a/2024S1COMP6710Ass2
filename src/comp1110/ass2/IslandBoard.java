package comp1110.ass2;

//  This class represents an island board in the game.

//  Authored by Yuxuan Shi U7789617.

import java.util.Random;

import static comp1110.ass2.Utility.RECTANGLE_BOARDS;
import static comp1110.ass2.Utility.SQUARE_BOARDS;

public class IslandBoard {

    //  ‘L' for 9 x 9 large square board. 'S' for 9 x 6 small rectangle board.
    private char type;

    //  A two-dimensional array represents its layout.
    private State[][] state;
    private Orientation orientation;


    /**
     * construct an island board.
     *
     * @param str two-character long string like "SA". 'A' means "any", means that the side without fire should be used.
     * @param ID  0, 1, 2, 3. indicate which board is chosen.
     */
    IslandBoard(String str, int ID) {
        this.type = str.charAt(0);

        int side = 0;
        if (str.charAt(1) == 'A') {
            side = 1;
        }
        this.orientation = Orientation.fromChar(str.charAt(1));

        String[][] s = SQUARE_BOARDS;
        int row = 9, col = 9;
        if (this.type == 'S') {
            s = RECTANGLE_BOARDS;
            row = 6;
        }

        this.state = new State[row][col];
        int index = 0;
        String utility = s[ID][side].replaceAll("\\s+", "");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.state[i][j] = State.fromChar(utility.charAt(index));
                index++;
            }
        }
    }


    //  square board rotate 90 degree clockwise
    public void square_rotate_90() {
        State[][] rotatedBoard = new State[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                rotatedBoard[j][8 - i] = this.state[i][j];
            }
        }

        this.state = rotatedBoard;
    }


    //  square board rotate according to predefined orientation
    public void square_rotate() {
        if (this.orientation == Orientation.EAST) {
            square_rotate_90();
        } else if (this.orientation == Orientation.SOUTH) {
            square_rotate_90();
            square_rotate_90();
        } else if (this.orientation == Orientation.WEST) {
            square_rotate_90();
            square_rotate_90();
            square_rotate_90();
        }
        else if(this.orientation == Orientation.NONE){
            Random random = new Random();
            int number = random.nextInt(4);

            while(number > 0){
                square_rotate_90();
                number --;
            }
        }
    }


    public String toString(){
        String str = "";
        str += this.type;
        str += this.orientation.toChar();
        return str;
    }



    public char getType() {
        return type;
    }

    public State[][] getState() {
        return state;
    }
}

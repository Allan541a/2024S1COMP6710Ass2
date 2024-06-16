package comp1110.ass2;

//  This class represents the current game's board state, via a two-dimensional array of type State.

//  Authored by Yuxuan Shi U7789617.

public class BoardState{
    //  A two-dimensional array of type State.
    State[][] state;



    /**
     * construct a board state.
     * @param str The "board" string in the "game state" string array.
     *            For example:
     * fffffffffrrfffffff
     * fffffffffrRfffffff
     * fffffffffrrfffffff
     * fffgffyrgpygyrygbr
     * fffgGfggyygprbprpg
     * fffgggbgprbpygbpyb
     * ffffffbpbpgrbrrbgy
     * ffffffgygybpgygprb
     * ffffffbrrrybgygybg
     * ffffffgpbbyrprgbbp
     * ffffffbyrbpybgpryg
     * ffffffbyrbpybgpryg
     * ffffffpgyrggrbgyby
     * fffffybgbpryybpgyp
     * ffffYyybpgbprygrow
     * fffyyyyryygbygybww
     */

    public BoardState(String str){
        String[] s = str.split("\n");
        int row = s.length;
        int col = s[0].length();
        this.state = new State[row][col];
        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                state[i][j] = State.fromChar(s[i].charAt(j));
            }
        }
    }

    /**
     * Creates an empty board of the specified size.
     * @param row Number of rows.
     * @param col Number of columns.
     */
    public BoardState(int row, int col){
        this.state = new State[row][col];
    }


    public State[][] getState() {
        return state;
    }


    //  Returns the "board" string in the "game state" string array.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (State[] row : state) {
            for (State square : row) {
                sb.append(square.toChar());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
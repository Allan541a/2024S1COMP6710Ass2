package comp1110.ass2;

//  this class represents the raft predefined by the challenge in the game.

//  Authored by Yuxuan Shi U7789617.

import static comp1110.ass2.Utility.RAFT_CARDS;

public class Raft {
    //  An array represents the layout of the raft.
    //  0 1 2
    //  3 4 5
    //  6 7 8
    private State[] state;
    //  The coordinates in the upper left corner of the raft.
    private Location location;

    private int ID;


    /**
     * construct a raft.
     * @param str 'raft' substring in a 'challenge' string, like "11215".
     */
    Raft(String str){
        String[] s = new String[(str.length() - 1) / 2];
        int index = 0;
        for (int i = 1; i< str.length(); i += 2) {
            s[index] = str.substring(i, i + 2);
            index ++;
        }

        int row = Integer.parseInt(s[0]);
        int col = Integer.parseInt(s[1]);
        this.location = new Location(row, col);

        this.ID = Character.getNumericValue(str.charAt(0));
        String utility = RAFT_CARDS[this.ID];
        char[] c;
        c = utility.toCharArray();
        this.state = new State[9];
        for(int x = 1; x < c.length; x ++){
            this.state[x - 1] = State.fromChar(c[x]);
        }
    }



    public String toString(){
        String str = "";
        str += String.valueOf(this.ID);
        str += String.format("%02d", this.location.getRow());
        str += String.format("%02d", this.location.getColumn());
        return str;
    }

    public State[] getState() {
        return state;
    }

    public Location getLocation() {
        return location;
    }
}

package comp1110.ass2;

//  This class represents a single cat card.

//  Authored by Yuxuan Shi U7789617.

import static comp1110.ass2.Utility.CAT_CARDS;

public class CatCard {

    //  The ID of this cat card.
    private int ID;

    //  The coordinates in the upper left corner of this cat card.
    private Location location;

    //  A State array represents the layout of this cat card.
    //  0 1 2
    //  3 4 5
    //  6 7 8
    private State[] state;


    /**
        construct a cat card.
     * @param str One of the cat cards in the "cat card locations" substring of the "challenge" string.
     *            For example: "00211" means that card 0 is placed at (02, 11).
     */
    public CatCard(String str){
        this.ID = Character.getNumericValue(str.charAt(0));

        int row = Integer.parseInt(str.substring(1, 3));
        int col = Integer.parseInt(str.substring(3));
        this.location = new Location(row, col);

        this.state = new State[9];
        String utility = CAT_CARDS[this.ID];
        for(int i = 0; i < 9; i ++){
            this.state[i] = State.fromChar(utility.charAt(i + 1));
        }
    }



    public String toString(){
        String str = String.valueOf(this.ID);
        str += String.format("%02d", this.location.getRow());
        str += String.format("%02d", this.location.getColumn());
        return str;
    }


    public Location getLocation() {
        return location;
    }

    public State[] getState() {
        return state;
    }
}

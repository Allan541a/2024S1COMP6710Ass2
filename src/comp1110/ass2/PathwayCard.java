package comp1110.ass2;

import java.util.Arrays;
import static comp1110.ass2.Utility.*;

//  Authored by Yuxuan Shi U7789617.

/**
 * This class represents a PathwayCard in the game.
 * The class encodes the ID of the card, the colour composition(state) of the card, and the orientation of the card.
 */

public class PathwayCard {
    private final char ID;
    /*
    An array containing nine elements representing the colours of the nine squares on the card.
    0 1 2
    3 4 5
    6 7 8
    */
    private State[] state;
    private Orientation orientation;
    private final char Deck_ID;


    /**
     * Construct a Pathway card from a 10-characters long string. The default orientation is NORTH.
     * @param str The first character is the ID and the second to tenth characters indicate the colour of each square.
     */
    public PathwayCard(String str, char Deck_ID){
        this.Deck_ID = Deck_ID;
        this.ID = str.charAt(0);
        this.state = new State[str.length() - 1];
        for(int i = 0; i < 9; i ++){
            this.state[i] = State.fromChar(str.charAt(i + 1));
        }
        this.orientation = Orientation.NORTH;
    }


    /**
     * construct a pathway card from a 2-characters long string.
     * @param str the first character represents the Deck, the second is the ID.
     */
    public PathwayCard(String str){
        this.Deck_ID = str.charAt(0);
        this.ID = str.charAt(1);

        String r = "";
        String[] s = new String[25];
        if(this.Deck_ID == 'A'){s = DECK_A;}
        else if (this.Deck_ID == 'B') {s = DECK_B;}
        else if (this.Deck_ID == 'C') {s = DECK_C;}
        else if (this.Deck_ID == 'D') {s = DECK_D;}
        for(int i = 0; i < s.length; i ++){
            if(s[i].charAt(0) == this.ID){
                r = s[i];
            }
        }

        this.state = new State[9];
        for(int i = 0; i < 9; i ++){
            this.state[i] = State.fromChar(r.charAt(i + 1));
        }

        this.orientation = Orientation.NORTH;
    }



    public String toString(){
        String str = "";
        str += this.ID;
        for(int i = 0; i < 9; i ++){
            str += this.state[i].toChar();
        }
        return str;
    }


    /**
     * Rotate the card 90 degrees clockwise. Change the colour layout and orientation of the card.
     * 0 1 2       6 3 0
     * 3 4 5   --> 7 4 1
     * 6 7 8       8 5 2
     */
    public void rotate90(){
        this.orientation = this.orientation.rotate();
        State[] newstate = Arrays.copyOf(this.state, 9);
        this.state[0] = newstate[6];
        this.state[1] = newstate[3];
        this.state[2] = newstate[0];
        this.state[3] = newstate[7];
        this.state[5] = newstate[1];
        this.state[6] = newstate[8];
        this.state[7] = newstate[5];
        this.state[8] = newstate[2];
    }


    /**
     * change the layout according to given orientation
     * @param o the orientation wanted
     */
    public void rotate(Orientation o){
        if(o == Orientation.EAST){
            rotate90();
        }
        else if(o == Orientation.SOUTH){
            rotate90();
            rotate90();
        }
        else if(o == Orientation.WEST){
            rotate90();
            rotate90();
            rotate90();
        }
    }


    public char getID(){
        return this.ID;
    }


    public char getDeck_ID() {
        return Deck_ID;
    }


    public State[] getState() {
        return state;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}

package comp1110.ass2;

//  This class represents all four decks of pathway cards.

//  Authored by Yuxuan Shi U7789617.

import java.util.Arrays;

public class Decks {

    private Deck[] decks;


    /**
     * Construct the decks.
     * @param str 'Decks' string in the 'game state' string array. Like "AabcdBCfghDafh".
     */

    public Decks(String str){
        this.decks = new Deck[4];
        String[] s = str.split("(?=[A-Z])");
        for(int i = 0; i < 4; i ++){
            decks[i] = new Deck(s[i]);
        }
    }


    /**
     * Update the decks.
     * @param str 'Decks' string in the 'game state' string array.
     */

    public void update(String str){
        this.decks = new Deck[4];
        String[] s = str.split("(?=[A-Z])");
        for(int i = 0; i < 4; i ++){
            decks[i] = new Deck(s[i]);
        }
    }


    /**
     * Check whether the 'drawRequest' string is valid, i.e. whether it is possible to draw all the specified cards.
     * @param str the 'drawRequest' string. Like "A3B1D2".
     * @return true if it's valid.
     */

    public boolean isRequestValid(String str){
        int request_A = 0, request_B = 0, request_C = 0, request_D = 0;
        for(int i = 0; i < str.length(); i += 2){
            if(str.charAt(i) == 'A'){request_A = Character.getNumericValue(str.charAt(i + 1));}
            else if(str.charAt(i) == 'B'){request_B = Character.getNumericValue(str.charAt(i + 1));}
            else if(str.charAt(i) == 'C'){request_C = Character.getNumericValue(str.charAt(i + 1));}
            else if(str.charAt(i) == 'D'){request_D = Character.getNumericValue(str.charAt(i + 1));}
        }

        int num_A = this.decks[0].getCards_in_deck().length;
        int num_B = this.decks[1].getCards_in_deck().length;
        int num_C = this.decks[2].getCards_in_deck().length;
        int num_D = this.decks[3].getCards_in_deck().length;

        if(num_A >= request_A & num_B >= request_B & num_C >= request_C & num_D >= request_D){
            return true;
        }
        return false;
    }



    public String toString(){
        return this.decks[0].toString() + this.decks[1].toString() + this.decks[2].toString() + this.decks[3].toString();
    }


    /**
     * Draw the specified number of cards according to the 'drawRequest' string and remove them.
     * @param str 'drawRequest' string. Like "A3B1D2".
     * @return a string represents the drawn cards, in the form of a 'Hand' string. Like "AfhkDahw".
     */

    public String draw_cards(String str){
        int request_A = 0, request_B = 0, request_C = 0, request_D = 0;
        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == 'A'){request_A = Character.getNumericValue(str.charAt(i + 1));}
            else if(str.charAt(i) == 'B'){request_B = Character.getNumericValue(str.charAt(i + 1));}
            else if(str.charAt(i) == 'C'){request_C = Character.getNumericValue(str.charAt(i + 1));}
            else if(str.charAt(i) == 'D'){request_D = Character.getNumericValue(str.charAt(i + 1));}
        }


        String result = "";
        result += 'A';
        if(request_A != 0){
            char[] draw_A = new char[request_A];
            for(int i = 0; i < request_A; i ++){
                draw_A[i] = this.decks[0].drawCard().getID();
            }
            Arrays.sort(draw_A);
            for(int i = 0; i < draw_A.length; i ++){
                result += draw_A[i];
            }
        }

        result += 'B';
        if(request_B != 0){
            char[] draw_B = new char[request_B];
            for(int i = 0; i < request_B; i ++){
                draw_B[i] = this.decks[1].drawCard().getID();
            }
            Arrays.sort(draw_B);
            for(int i = 0; i < draw_B.length; i ++){
                result += draw_B[i];
            }
        }
        result += 'C';
        if(request_C != 0){
            char[] draw_C = new char[request_C];
            for(int i = 0; i < request_C; i ++){
                draw_C[i] = this.decks[2].drawCard().getID();
            }
            Arrays.sort(draw_C);
            for(int i = 0; i < draw_C.length; i ++){
                result += draw_C[i];
            }
        }
        result += 'D';
        if(request_D != 0){
            char[] draw_D = new char[request_D];
            for(int i = 0; i < request_D; i ++){
                draw_D[i] = this.decks[3].drawCard().getID();
            }
            Arrays.sort(draw_D);
            for(int i = 0; i < draw_D.length; i ++){
                result += draw_D[i];
            }
        }
        return result;
    }


    public Deck[] getDecks() {
        return decks;
    }
}

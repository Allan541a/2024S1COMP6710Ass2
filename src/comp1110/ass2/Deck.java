package comp1110.ass2;

//  This class represents a single pathway card deck.

//  Authored by Yuxuan Shi U7789617.

import java.util.Random;

import static comp1110.ass2.Utility.*;

public class Deck {
    //  An array containing the remaining pathway cards in this deck.
    private PathwayCard[] cards_in_deck;

    //  Deck's ID. Only four types: 'A', 'B', 'C' and 'D'.
    private char ID;


        /**
         * Construct a deck.
         * @param str A string starts with the deck ID, followed by the ID of each card in the deck in alphabetical order.
         *            A substring of the "decks" string in the "game state" array.
         */
    public Deck(String str){
        this.ID = str.charAt(0);
        String[] s = new String[25];
        if(this.ID == 'A'){s = DECK_A;}
        else if (this.ID == 'B') {s = DECK_B;}
        else if (this.ID == 'C') {s = DECK_C;}
        else if (this.ID == 'D') {s = DECK_D;}
        this.cards_in_deck = new PathwayCard[str.length() - 1];

        for(int i = 0; i < str.length() - 1; i ++){
            for(int j = 0; j < s.length; j ++){
                if(str.charAt(i + 1) == s[j].charAt(0)){
                    cards_in_deck[i] = new PathwayCard(s[j], this.ID);
                    break;
                }
            }
        }
    }


    /**
     * Draw a pathway card from the deck. Then delete the drawn card from this deck.
     * @return A pathway card randomly selected from this deck.
     */
    public PathwayCard drawCard(){
        Random random = new Random();
        int n = random.nextInt(cards_in_deck.length);

        PathwayCard chosen_card = cards_in_deck[n];
        PathwayCard[] changed_deck = new PathwayCard[cards_in_deck.length - 1];

        int index = 0;
        for(int i = 0; i < cards_in_deck.length; i ++){
            if(i != n){
                changed_deck[index] = cards_in_deck[i];
                index ++;
            }
        }

        this.cards_in_deck = changed_deck;

        return chosen_card;
    }




    public String toString(){
        String str = "";
        str += this.ID;
        for(int i = 0; i < cards_in_deck.length; i ++){
            str += cards_in_deck[i].getID();
        }
        return str;
    }



    public PathwayCard[] getCards_in_deck() {
        return cards_in_deck;
    }
}

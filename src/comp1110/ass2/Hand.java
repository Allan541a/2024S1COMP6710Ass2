package comp1110.ass2;

//  This class represents the player's hand in the game.

//  Authored by Yuxuan Shi U7789617.

import java.util.ArrayList;

import static comp1110.ass2.Utility.*;

public class Hand {

    //  An array containing all the pathway cards in hand now.
    private PathwayCard[] cards_in_hand;


    /**
     * Construct a hand.
     * @param str A string like 'AfhkBCDahw', the same way that the decks are represented but list only the cards that we have.
     *            'Hand' string in 'game state' array.
     */
    public Hand(String str) {
        ArrayList<PathwayCard> list = new ArrayList<>();
        String[] Deck_str = new String[25];
        char Deck_ID = ' ';
        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == 'A'){
                Deck_str = DECK_A;
                Deck_ID = 'A';
                continue;
            }
            else if (str.charAt(i) == 'B') {
                Deck_str = DECK_B;
                Deck_ID = 'B';
                continue;
            }
            else if (str.charAt(i) == 'C') {
                Deck_str = DECK_C;
                Deck_ID = 'C';
                continue;
            }
            else if (str.charAt(i) == 'D') {
                Deck_str = DECK_D;
                Deck_ID = 'D';
                continue;
            }
            for(int j = 0; j < 25; j ++){
                if(str.charAt(i) == Deck_str[j].charAt(0)){
                    list.add(new PathwayCard(Deck_str[j], Deck_ID));
                    break;
                }
            }
        }
        this.cards_in_hand = list.toArray(new PathwayCard[list.size()]);
    }



    public void discard_card(PathwayCard card){
        PathwayCard[] list = new PathwayCard[cards_in_hand.length - 1];
        int index = 0;
        for (PathwayCard pathwayCard : cards_in_hand) {
            if (card.getID() != pathwayCard.getID() || card.getDeck_ID() != pathwayCard.getDeck_ID()) {
                list[index] = pathwayCard;
                index++;
            }
        }
        this.cards_in_hand = list;
    }


    public PathwayCard[] getCards_in_hand() {
        return cards_in_hand;
    }


    public String toString() {
        StringBuilder strA = new StringBuilder("A");
        StringBuilder strB = new StringBuilder("B");
        StringBuilder strC = new StringBuilder("C");
        StringBuilder strD = new StringBuilder("D");

        for (PathwayCard card : cards_in_hand) {
            if (card != null) {
                switch (card.getDeck_ID()) {
                    case 'A':
                        strA.append(card.getID());
                        break;
                    case 'B':
                        strB.append(card.getID());
                        break;
                    case 'C':
                        strC.append(card.getID());
                        break;
                    case 'D':
                        strD.append(card.getID());
                        break;
                    default:
                        break;
                }
            }
        }
        strA.append(strB).append(strC).append(strD);
        return strA.toString();
    }

}

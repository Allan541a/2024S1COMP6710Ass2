package comp1110.ass2;

//  This class represents all the cat cards in the game, acts as a container for the cat cards.

//  Authored by Yuxuan Shi U7789617.

public class CatCards {

    CatCard[] catCards;


    /**
        Construct the cat cards predefined by the challenge.
     * @param str The "cat card locations" substring of the "challenge" string.
     */
    public CatCards(String str){
        int num = str.length()/ 5;
        this.catCards = new CatCard[num];
        int index = 0;
        for(int i = 0; i < num; i ++){
            String s = str.substring(index, index + 5);
            this.catCards[i] = new CatCard(s);
            index += 5;
        }
    }



    public String toString(){
        String str = "";
        for(int i = 0; i < catCards.length; i ++){
            str += catCards[i].toString();
        }
        return str;
    }
}

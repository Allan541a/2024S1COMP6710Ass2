package comp1110.ass2;

//  This class represents the fire tile bag in the game.

//  Authored by Yuxuan Shi U7789617.

import java.util.Random;

import static comp1110.ass2.Utility.FIRE_TILES;

public class FireTileBag {

    //  An array containing the remaining fire tiles.
    private FireTile[] bag;


    /**
     *
     * @param str The "fire tile bag" string in the "game state" string array.
     */
    public FireTileBag(String str) {
        if (str == "") {
            this.bag = null;
        } else {
            this.bag = new FireTile[str.length()];
            for (int i = 0; i < str.length(); i++) {
                for (int j = 0; j < 31; j++) {
                    if (str.charAt(i) == FIRE_TILES[j].charAt(0)) {
                        this.bag[i] = new FireTile(FIRE_TILES[j]);
                        break;
                    }
                }
            }
        }
    }


    /**
     * update the fire tile bag.
     * @param str The "fire tile bag" string in the "game state" string array.
    */
    public void update(String str){
        this.bag = new FireTile[str.length()];
        for(int i = 0; i < str.length(); i ++){
            for(int j = 0; j < 31; j ++){
                if(str.charAt(i) == FIRE_TILES[j].charAt(0)){
                    this.bag[i] = new FireTile(FIRE_TILES[j]);
                    break;
                }
            }
        }
    }


    public String toString(){
        String str = "";
        for(int i = 0; i < bag.length; i ++){
            str += bag[i].getID();
        }
        return str;
    }


    public FireTile[] getBag() {
        return bag;
    }


    /**
     * Draws a random fire tile from those remaining in the bag, and remove it from the bag.
     * @return a random fire tile in string form.
     */
    public String draw_tile(){
        Random random = new Random();
        int n = random.nextInt(this.bag.length);
        String s = this.bag[n].toString();

        FireTile[] new_bag = new FireTile[this.bag.length - 1];
        int index = 0;
        for(int i = 0; i < this.bag.length; i ++){
            if(i != n){
                new_bag[index] = this.bag[i];
                index ++;
            }
        }
        this.bag = new_bag;
        return s;
    }
}

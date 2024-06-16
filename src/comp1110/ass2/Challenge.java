package comp1110.ass2;

//  This class represents the challenges in the game.
//  Acts as a container for craft, catcards, firecards, islandboards.

//  Authored by Yuxuan Shi U7789617.

import java.util.Random;

import static comp1110.ass2.Utility.CHALLENGES;

public class Challenge {
    private Raft raft;
    private CatCards catcards;
    private FireCards firecards;
    private IslandBoards islandboards;


    /**
     * Construct a challenge.
     * @param str The "challenge" string.
     *            For example: "LNSNLASAF000300060012001503030903C112033060340009R01215".
     */
    public Challenge(String str){
        String[] s = str.split("[FCR]");
        this.islandboards = new IslandBoards(s[0]);
        this.firecards = new FireCards(s[1]);
        this.catcards = new CatCards(s[2]);
        this.raft = new Raft(s[3]);
    }



    //  Empty Parameter Construction.
    public Challenge() {}



    /**
     * @param difficulty Difficulty level, 0 - 5.
     * @return A random challenge of the given difficulty。
     */
    public static Challenge newChallenge(int difficulty){
        Random random = new Random();
        int ID = 0;
        if(difficulty == 0){
            ID = random.nextInt(4);
        }
        else if(difficulty == 1){
            ID = random.nextInt(4) + 4;
        }
        else if(difficulty == 2){
            ID = random.nextInt(8) + 8;
        }
        else if(difficulty == 3){
            ID = random.nextInt(8) + 16;
        }
        else if(difficulty == 4){
            ID = random.nextInt(8) + 24;
        }
        else if(difficulty == 5){
            ID = random.nextInt(7) + 32;
        }
        return new Challenge(CHALLENGES[ID]);
    }



    public String toString(){
        String str = this.islandboards.toString() + "F" + this.firecards.toString() + "C"
                + this.catcards.toString() + "R" + this.raft.toString();
        return str;
    }


    public Raft getRaft(){
        return raft;
    }

    public CatCards getCatcards() {
        return catcards;
    }

    public FireCards getFirecards() {
        return firecards;
    }

    public IslandBoards getIslandboards() {
        return islandboards;
    }
}

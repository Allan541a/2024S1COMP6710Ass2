package comp1110.ass2;

//  Two or four island board.

//  Authored by Yuxuan Shi U7789617.

import java.util.Random;

public class IslandBoards {

    //  0 2
    //  1 3
    IslandBoard[] islandboards;


    /**
     * construct island boards.
     * @param str like "LNSNLASA".
     *            "island" substring in "challenge" string
     */

    IslandBoards(String str){
        int num = str.length() / 2;
        this.islandboards = new IslandBoard[num];
        Random random = new Random();

        if(num == 2 && str.charAt(0) == 'L' && str.charAt(2) == 'S'){
            int LID = random.nextInt(4);
            int SID = random.nextInt(4);
            this.islandboards[0] = new IslandBoard(str.substring(0, 2), LID);
            this.islandboards[1] = new IslandBoard(str.substring(2, 4), SID);
        }
        else if(num == 4 && str.charAt(0) == 'L' && str.charAt(2) == 'S' && str.charAt(4) == 'L' && str.charAt(6) == 'S'){
            int LID1 = random.nextInt(4);
            int LID2;
            do {
                LID2 = random.nextInt(4);
            } while (LID2 == LID1);
            int SID1 = random.nextInt(4);
            int SID2;
            do {
                SID2 = random.nextInt(4);
            } while (SID2 == SID1);

            this.islandboards[0] = new IslandBoard(str.substring(0, 2), LID1);
            this.islandboards[1] = new IslandBoard(str.substring(2, 4), SID1);
            this.islandboards[2] = new IslandBoard(str.substring(4, 6), LID2);
            this.islandboards[3] = new IslandBoard(str.substring(6, 8), SID2);
        }
        else if(num == 2 && str.charAt(0) == 'L' && str.charAt(2) == 'L'){
            int LID1 = random.nextInt(4);
            int LID2;
            do {
                LID2 = random.nextInt(4);
            } while (LID2 == LID1);
            this.islandboards[0] = new IslandBoard(str.substring(0, 2), LID1);
            this.islandboards[1] = new IslandBoard(str.substring(2, 4), LID2);
        }
        else if(num == 4 && str.charAt(0) == 'S' && str.charAt(2) == 'S' && str.charAt(4) == 'S' && str.charAt(6) == 'S'){
            int SID1 = random.nextInt(4);
            int SID2;
            int SID3;
            int SID4;
            do {
                SID2 = random.nextInt(4);
            } while (SID2 == SID1);
            do {
                SID3 = random.nextInt(4);
            } while (SID3 == SID1 || SID3 == SID2);
            do {
                SID4 = random.nextInt(4);
            } while (SID4 == SID1 || SID4 == SID2 || SID4 == SID3);

            this.islandboards[0] = new IslandBoard(str.substring(0, 2), SID1);
            this.islandboards[1] = new IslandBoard(str.substring(2, 4), SID2);
            this.islandboards[2] = new IslandBoard(str.substring(4, 6), SID3);
            this.islandboards[3] = new IslandBoard(str.substring(6, 8), SID4);
        }
    }


    public String toString(){
        String str = "";
        for(IslandBoard board: this.islandboards){
            str += board.toString();
        }
        return str;
    }

}

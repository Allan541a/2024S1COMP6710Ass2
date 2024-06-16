package comp1110.ass2;

//  This class represents all the fire cards predefined by the challenge.
//  Fire card are 3 x 3 card containing only fire.

//  Authored by Yuxuan Shi U7789617.

public class FireCards {

    //  The locations in the upper left corner of each fire card.
    private Location[] locations;


    /**
     * @param str 'Fire' substring in 'Challenge' string. Like "10030409"
     */
    public FireCards(String str){
        int num = str.length() / 4;
        this.locations = new Location[num];
        int index = 0;
        for(int i = 0; i < num; i++){
            int row = Integer.parseInt(str.substring(index, index + 2));
            int col = Integer.parseInt(str.substring(index + 2, index + 4));
            this.locations[i] = new Location(row, col);
            index += 4;
        }
    }


    public String toString(){
        String str = "";
        for(int i = 0; i < locations.length; i ++){
            str += String.format("%02d", locations[i].getRow());
            str += String.format("%02d", locations[i].getColumn());
        }
        return str;
    }


    public Location[] getLocations() {
        return locations;
    }
}

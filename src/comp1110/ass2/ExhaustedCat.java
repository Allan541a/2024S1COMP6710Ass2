package comp1110.ass2;

//  This class represents an exhausted cat in the game.

//  Authored by Yuxuan Shi U7789617.

public class ExhaustedCat {
    //  a char 'B','G','P','R','Y','W' representing the colour.
    private char colour;
    //  coordinates of the cat.
    private Location location;


    /**
     * Construct an exhausted cat.
     * @param str a string like "B0312".
     *            Substring of the "exhausted cats" string in the "game state" string array.
     */
    public ExhaustedCat(String str){
        this.colour = str.charAt(0);
        int row = Integer.parseInt(str.substring(1, 3));
        int col = Integer.parseInt(str.substring(3));
        this.location = new Location(row, col);
    }



    public String toString(){
        String str = Character.toString(this.colour);
        str += String.format("%02d", this.location.getRow());
        str += String.format("%02d", this.location.getColumn());
        return str;
    }


    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    public char getColour() {
        return colour;
    }
}

package comp1110.ass2;

//  This class represents a Fire Tile in the game.

//  Authored by Yuxuan Shi U7789617.

public class FireTile {
    //  A unique character ID between 'a' to 'z' or 'A' to 'E'.
    private final char ID;

    //  An array contains coordinates in the 9 x 9 range relative to (0,0), represents its shape.
    private Location[] layout;


    /**
     * Construct a fire tile.
     * @param str the first character is its ID. the rest is made up of 2-character substrings that represent the coordinates.
     *            From "FIRE_TILES" string array in UTILITY
    */
    public FireTile(String str){
        this.ID = str.charAt(0);
        this.layout = new Location[(str.length() - 1) / 2];

        int index = 0;
        for(int i = 1; i < str.length(); i = i + 2){
            int row = Character.getNumericValue(str.charAt(i));
            int column = Character.getNumericValue(str.charAt(i + 1));
            this.layout[index] = new Location(row, column);
            index ++;
        }
    }



    public String toString(){
        String str = "";
        str += this.ID;
        for(int i = 0; i < this.layout.length; i ++){
            str += this.layout[i].getRow();
            str += this.layout[i].getColumn();
        }
        return str;
    }


    //  Rotate the fire tile 90 degrees clockwise, change its layout.
    public void rotate90(){
        for (int i = 0; i < layout.length; i++) {
            int newRow = layout[i].getColumn();
            int newCol = 8 - layout[i].getRow();
            layout[i] = new Location(newRow, newCol);
        }
    }


    /**
     * Change the layout according to given orientation.
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


    //  Flip the fire tile, change its layout.
    public void flip(){
        for (int i = 0; i < layout.length; i++) {
            int newRow = layout[i].getRow();
            int newCol = 8 - layout[i].getColumn();
            layout[i] = new Location(newRow, newCol);
        }
    }


    public char getID() {
        return ID;
    }

    public Location[] getLayout() {
        return layout;
    }
}

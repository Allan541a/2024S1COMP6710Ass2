package comp1110.ass2;

//  Authored by Yuxuan Shi U7789617.

/**
 * This class encodes a certain cartesian coordinate system (row, column).
 * The location of the square in the upper left corner is (0, 0).
 * Values are increasing to the right or downwards.
*/

public class Location {
    //  The Location's row-coordinate.
    private int row;
    //  The Location's column-coordinate.
    private int column;

    //  Create a Location that is not on the island.
    public Location() {
        this.row = -1;
        this.column = -1;
    }


    /**
     * Create a new location, given a row and column-coordinate.
     * @param row    the row-coordinate.
     * @param column the column-coordinate.
     */
    public Location(int row, int column){
        this.row = row;
        this.column = column;
    }


    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}

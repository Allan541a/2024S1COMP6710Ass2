package comp1110.ass2;

//  This enumeration type represents the four cardinal orientations.

//  Authored by Yuxuan Shi U7789617.

public enum Orientation {
    NORTH, EAST, SOUTH, WEST, NONE;

    /**
     * Rotate this orientation 90 degrees clockwise.
     * @return the rotated orientation
     */
    public Orientation rotate() {
        return values()[(this.ordinal() + 1) % 4];
    }


    /**
     * Given a character, return the Orientation associated with this character.
     * If the character doesn't have an associated Orientation, return `NONE`.
     * @param orientation the char to convert to a orientation.
     * @return the associated orientation for the char, or NONE if there isn't one.
     */
    public static Orientation fromChar(char orientation) {
        return switch (orientation) {
            case 'N' -> NORTH;
            case 'E' -> EAST;
            case 'S' -> SOUTH;
            case 'W' -> WEST;
            default -> NONE;
        };
    }


    /**
     * @return the representative char for this orientation.
     */
    public char toChar() {
        return switch (this) {
            case NORTH -> 'N';
            case EAST -> 'E';
            case SOUTH -> 'S';
            case WEST -> 'W';
            default -> 'A';
        };
    }
}


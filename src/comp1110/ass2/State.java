package comp1110.ass2;

//  Authored by Yuxuan Shi U7789617.

    /*
    This enumeration type represents the state of a single square in the game.

    'b - BLUE' for blue square.
    'r - RED' for red square.
    'y - YELLOW' for yellow square.
    'p - PURPLE' for purple square.
    'g - GREEN' for green square.
    'o - OBJECTIVE' for raft square("o" for objective), only the centre square of the raft card is labelled o.
        The objective space o cannot be occupied by a cat and as such is never represented by a capital letter.
    'w - WILD' for wildcard square (square on which any cat can reside).
    'f - FIRE' for square on fire.
    'B - BLUE_CAT' for blue cat on blue square.
    'R - RED_CAT' for red cat on red square.
    'Y - YELLOW_CAT' for yellow cat on yellow square.
    'P - PURPLE_CAT' for purple cat on purple square.
    'G - GREEN_CAT' for green cat on green square.
    'W - WILD_CAT' for any cat on wildcard square.
    'x - NONE' for default.
    */

public enum State {
    BLUE, RED, YELLOW, PURPLE, GREEN, OBJECTIVE, WILD, FIRE,
    BLUE_CAT, RED_CAT, YELLOW_CAT, PURPLE_CAT, GREEN_CAT, WILD_CAT,
    NONE;


    /**
     * Given a character, return the State associated with this character.
     * If the character doesn't have an associated State, return `NONE`.
     * @param state the char to convert to a state.
     * @return the associated state for the char, or NONE if there isn't one.
     */
    public static State fromChar(char state) {
        return switch (state) {
            case 'b' -> BLUE;
            case 'r' -> RED;
            case 'y' -> YELLOW;
            case 'p' -> PURPLE;
            case 'g' -> GREEN;
            case 'o' -> OBJECTIVE;
            case 'w' -> WILD;
            case 'f' -> FIRE;
            case 'B' -> BLUE_CAT;
            case 'R' -> RED_CAT;
            case 'Y' -> YELLOW_CAT;
            case 'P' -> PURPLE_CAT;
            case 'G' -> GREEN_CAT;
            case 'W' -> WILD_CAT;
            default -> NONE;
        };
    }


    /**
     * @return the representative char for this state.
     */
    public char toChar() {
        return switch (this) {
            case BLUE -> 'b';
            case RED -> 'r';
            case YELLOW -> 'y';
            case PURPLE -> 'p';
            case GREEN -> 'g';
            case OBJECTIVE -> 'o';
            case WILD -> 'w';
            case FIRE -> 'f';
            case BLUE_CAT -> 'B';
            case RED_CAT -> 'R';
            case YELLOW_CAT -> 'Y';
            case PURPLE_CAT -> 'P';
            case GREEN_CAT -> 'G';
            case WILD_CAT -> 'W';
            default -> 'x';
        };
    }

}

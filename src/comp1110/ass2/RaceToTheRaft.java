package comp1110.ass2;

import java.util.Arrays;
import java.util.Random;

import static comp1110.ass2.Utility.*;

/**
 * This class is for testing purposes only. You should create and use your own objects to solve the tasks below
 * instead of directly using the strings provided. Note that Task 2 is the only task you are expected to use string
 * manipulation to solve.
 */

public class RaceToTheRaft {

    /**
     * Determine whether a boardState string is well-formed.
     * To be well-formed the string must satisfy all the following conditions:
     * <p>
     * 1. Each line is terminated by a newline character `\n`
     * 2. The number of printable (non-newline) characters in each line is equal AND is either 9 or 18.
     * 3. Each character (except the newline character) is one of the following:
     * - 'b' (blue)
     * - 'B' (blue with blue cat)
     * - 'f' (fire)
     * - 'g' (green)
     * - 'G' (green with green cat)
     * - 'n' (none)
     * - 'o' (objective)
     * - 'p' (purple)
     * - 'P' (purple with purple cat)
     * - 'r' (red)
     * - 'R' (red with red cat)
     * - 'w' (wild)
     * - 'W' (wild with a cat of any colour)
     * - 'y' (yellow)
     * - 'Y' (yellow with yellow cat)
     * 4. The number of lines is either 12, 15 or 18.
     * </p>
     *
     * @param boardString A string representing the boardState
     * @return True if the boardState is well-formed, otherwise false.
     */


    //  Authored by Yuxuan Shi U7789617.
    public static boolean isBoardStringWellFormed(String boardString) {
        //  Each line is terminated by a newline character `\n`.
        //  We only need to verify that the end of the last line is "\n".
        //  This is because if there is a problem with the previous line it will cause the rest of the validation to fail later.
            if(!boardString.endsWith("\n")){
                return false;
            }

        //  Split the string by "\n", the result does not contain "\n".
        String []str = boardString.split("\n");

        //  The number of lines is either 12, 15 or 18.
        int num_of_lines = str.length;
        if(num_of_lines != 12 & num_of_lines != 15 & num_of_lines != 18){
            return false;
        }

        //  The number of printable (non-newline) characters in each line is equal AND is either 9 or 18.
        for(int i = 1; i < num_of_lines; i ++){
            if(str[0].length() != str[i].length()){
                return false;
            }
        }
        if(str[0].length() != 9 & str[0].length() != 18){
            return false;
        }

        //  Each character (except the newline character) is one of the following.
        for(int i = 0; i < num_of_lines; i ++){
            char []c = str[i].toCharArray();
            for(int j = 0; j < c.length; j ++){
                if(c[j] != 'b' & c[j] != 'B' & c[j] != 'f' & c[j] != 'g' & c[j] != 'G' & c[j] != 'n' & c[j] != 'o'
                        & c[j] != 'p' & c[j] != 'P' & c[j] != 'r' & c[j] != 'R' & c[j] != 'w' & c[j] != 'W'
                        & c[j] != 'y' & c[j] != 'Y'){
                    return false;
                }
            }
        }
        return true; // FIXME TASK 2
    }


    /*
     * Make Constructors for each of your objects.
     */

    //  Some of the requested functionality is already implemented in the corresponding classes.
    //  Authored by Yuxuan Shi U7789617.

    private static String[] game_state;
    //  Initialise the full fire tile bag.
    private static FireTileBag firetilebag = new FireTileBag("abcdefghijklmnopqrstuvwxyzABCDE");

    //  Initialise the full decks.
    private static Decks decks = new Decks("AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy");

    //  Initialise the board state in the game.
    private static BoardState board;

    //  Initialise the empty hand.
    private static Hand hand = new Hand("");

    //  Initialise the empty exhausted cats.
    private static ExhaustedCats exhaustedcats = new ExhaustedCats("");

    // FIXME TASK 3


    /**
     * Draws a random fire tile from those remaining in the bag.
     *
     * @param gameState the current state of the game, including the fire tile bag
     * @return a random fire tile from those remaining, in string form. If there are no tiles remaining, return the
     * empty string.
     */

    //  Authored by Yuxuan Shi U7789617.
    public static String drawFireTile(String[] gameState) {
        firetilebag.update(gameState[4]);
        if(gameState[4] == ""){
            return "";
        }
        else{
            String s = firetilebag.draw_tile();
            return s;
        } // FIXME TASK 5
    }


    /**
     * Chooses a random challenge from those available in the Utility class according
     * to the given difficulty.
     *
     * @param difficulty the desired difficulty of the challenge
     * @return a random challenge of the given difficulty
     */

    //  Authored by Yuxuan Shi U7789617.
    public static String chooseChallenge(int difficulty) {
        Challenge challenge = Challenge.newChallenge(difficulty);
        return challenge.toString(); // FIXME TASK 6
    }


    /**
     * Draw random cards from the specified decks.
     * The decks string denotes what decks to draw from and how many cards to draw from that deck.
     * <p>
     * For example the drawRequest string "A4B1D1" tells us that we should draw 4 cards from deck A, 1 card from deck B
     * and
     * 1 card from deck D.
     * <p>
     * If I draw cards a, b, d, l, from deck A, card a from deck B and card s from deck D, I would return the string:
     * "AabdlBaCDs".
     * Decks should be listed in alphabetical order, with cards drawn from that deck also listed in alphabetical order.
     * </p>
     * Recall the mapping between deck and char:
     * A -> CIRCLE;
     * B -> CROSS;
     * C -> SQUARE;
     * D -> TRIANGLE;
     *
     * @param gameState   the current state of the game, including the current state of the decks
     * @param drawRequest A string representing the decks to draw from and the number of cards to draw from each respective
     *                    deck.
     * @return The updated gameState array after the cards have been drawn. (Remove all cards drawn from decks and
     * add them to the Hand string). If it is not possible
     * to draw all the specified cards, you should return the original gameState.
     */

    //  Authored by Yuxuan Shi U7789617.
    public static String[] drawHand(String[] gameState, String drawRequest) {
        Decks decks = new Decks(gameState[1]);
        if(!decks.isRequestValid(drawRequest)){
            return gameState;
        }
        String[] nextState = Arrays.copyOf(gameState, gameState.length);
        nextState[2] = decks.draw_cards(drawRequest);
        nextState[1] = decks.toString();
        return nextState;// FIXME TASK 7
    }


    /**
     * Place the given card or fire tile as described by the placement string and return the updated gameState array.
     * See the README for details on these two strings.
     * You may assume that the placements given are valid.
     * <p>
     * When placing a card, you should update both the Board string and remove the corresponding card from the Hand
     * string in the gameState array.
     *
     * @param gameState       An array representing the game state.
     * @param placementString A string representing a Fire Tile Placement or a Card Placement.
     * @return the updated gameState array after this placement has been made
     */

    //  Authored by Yuxuan Shi U7789617.
    public static String[] applyPlacement(String[] gameState, String placementString) {
        BoardState board = new BoardState(gameState[0]);
        Hand hand = new Hand(gameState[2]);

        if(Character.isLetter(placementString.charAt(1))){
            //  pathway Card placement
            PathwayCard card = new PathwayCard(placementString.substring(0, 2));
            int row = Integer.parseInt(placementString.substring(2, 4));
            int col = Integer.parseInt(placementString.substring(4, 6));
            Orientation orientation = Orientation.fromChar(placementString.charAt(6));

            card.rotate(orientation);

            //  place the card, update the board state
            int index = 0;
            for (int i = row; i < row + 3; i++) {
                for (int j = col; j < col + 3; j++) {
                    board.state[i][j] = card.getState()[index++];
                }
            }

            //  remove from hand
            hand.discard_card(card);

            String[] newState = Arrays.copyOf(gameState, gameState.length);
            newState[0] = board.toString();
            newState[2] = hand.toString();

            return newState;
        }

        else{
            //  File Tile placement
            String str = "";
            for(String s: FIRE_TILES){
                if(s.charAt(0) == placementString.charAt(0)){
                    str = s;
                }
            }
            FireTile tile = new FireTile(str);

            int row = Integer.parseInt(placementString.substring(1, 3));
            int col = Integer.parseInt(placementString.substring(3, 5));
            Orientation orientation = Orientation.fromChar(placementString.charAt(6));

            if(placementString.charAt(5) == 'T'){
                tile.flip();
            }

            tile.rotate(orientation);

            //  reposition coordinates
            int min_row = tile.getLayout()[0].getRow();
            int min_col = tile.getLayout()[0].getColumn();
            for(Location l: tile.getLayout()){
                if(l.getRow() < min_row){
                    min_row = l.getRow();
                }
                if(l.getColumn() < min_col){
                    min_col = l.getColumn();
                }
            }

            for(Location l: tile.getLayout()){
                l.setRow(l.getRow() - min_row);
                l.setColumn(l.getColumn() - min_col);
            }

            //  By this point, the shape of the flame bar has been rotated and flipped,
            //  and repositioned to the immediate upper left corner of the 9*9.


            for(Location l: tile.getLayout()){
                int row_project = l.getRow() + row;
                int col_project = l.getColumn() + col;

                board.state[row_project][col_project] = State.FIRE;
            }

            String[] newState = Arrays.copyOf(gameState, gameState.length);
            newState[0] = board.toString();

            return newState;
        }
        // FIXME TASK 8
    }


    /**
     * Move the given cat as described by the cat movement string and return the updated gameState array. You may
     * assume that the cat movement is valid.
     * <p>
     * You should both move the cat (updating the Board string) and also add the cat to the Exhausted Cats string, or
     * update that cat's reference in the Exhausted Cats string if it was already exhausted.
     *
     * @param gameState      An array representing the game state.
     * @param movementString A string representing the movement of a cat and the cards discarded to allow this move.
     * @return the updated gameState array after this movement has been made.
     */

    //  Authored by Yuxuan Shi U7789617.
    public static String[] moveCat(String[] gameState, String movementString) {

        BoardState board = new BoardState(gameState[0]);
        Hand hand = new Hand(gameState[2]);
        ExhaustedCats cats = new ExhaustedCats(gameState[3]);

        //  move cat, change board state
        int row_start = Integer.parseInt(movementString.substring(1,3));
        int col_start = Integer.parseInt(movementString.substring(3,5));
        int row_end = Integer.parseInt(movementString.substring(5,7));
        int col_end = Integer.parseInt(movementString.substring(7,9));

        board.state[row_start][col_start] = State.fromChar(movementString.substring(0,1).toLowerCase().charAt(0));
        if(board.state[row_end][col_end].toChar() == 'w'){
            board.state[row_end][col_end] = State.WILD_CAT;
        }else {
            board.state[row_end][col_end] = State.fromChar(movementString.substring(0, 1).charAt(0));
        }


        //  update the exhausted cats string. (update the coordinate if already exists, add one if not exists)
        String cats_list = gameState[3];
        if(cats_list.contains(movementString.substring(0,1))){
            for(ExhaustedCat c: cats.exhaustedcats){
                if(c.toString().charAt(0) == movementString.charAt(0)){
                    c.setLocation(new Location(row_end, col_end));
                }
            }
        }
        else{
            ExhaustedCat new_cat = new ExhaustedCat(movementString.substring(0,1) + movementString.substring(5,9));
            cats.add_cat(new_cat);
        }


        //  discard cards
        String cards = movementString.substring(9);
        if(cards.length() == 2){
            hand.discard_card(new PathwayCard(cards));
        }
        else if(cards.length() == 3){
            String card1 = String.valueOf(cards.charAt(0) + cards.charAt(1));
            String card2 = String.valueOf(cards.charAt(0) + cards.charAt(2));
            hand.discard_card(new PathwayCard(card1));
            hand.discard_card(new PathwayCard(card2));
        }
        else if(cards.length() == 4){
            String card1 = cards.substring(0, 2);
            String card2 = cards.substring(2);
            hand.discard_card(new PathwayCard(card1));
            hand.discard_card(new PathwayCard(card2));
        }


        String[] newState = Arrays.copyOf(gameState, gameState.length);
        newState[0] = board.toString();
        newState[2] = hand.toString();
        newState[3] = cats.toString();

        return newState; // FIXME TASK 9
    }


    /**
     * Given a challengeString, construct a board string that satisfies the challenge requirements.
     * <p>
     * Each board in the `squareBoard` or `rectangleBoard` arrays may only be used once. For example: if the
     * challenge requires 4 Large (square) boards, you must use all 4 square boards. You may not use the same board
     * twice, even in different orientations.
     * The cat, fire card and raft card placements should all match the challenge string.
     *
     * @param challengeString A string representing the challenge to initialise
     * @return A board string for this challenge.
     */

    //  Authored by Yuxuan Shi U7789617.
    public static String initialiseChallenge(String challengeString) {

        String[] str_list = challengeString.split("[FCR]");
        String island = str_list[0];
        String fire = str_list[1];
        String cat = str_list[2];
        String raft = str_list[3];

        //  island
        IslandBoards boards = new IslandBoards(island);

        for(IslandBoard board: boards.islandboards){
            if(board.getType() =='L'){
                board.square_rotate();
            }
        }

        BoardState state = new BoardState(15,18);

        if(boards.islandboards.length == 2 && boards.islandboards[0].getType() == 'L' && boards.islandboards[1].getType() == 'S'){
            State[][] boardstate = new State[15][9];

            for (int i = 0; i < boards.islandboards[0].getState().length; i++) {
                boardstate[i] = boards.islandboards[0].getState()[i];
            }

            for (int i = 0; i < boards.islandboards[1].getState().length; i++) {
                boardstate[9 + i] = boards.islandboards[1].getState()[i];
            }

            state.state = boardstate;
        }

        else if(boards.islandboards[0].getType() == 'L' && boards.islandboards[1].getType() == 'S' && boards.islandboards[2].getType() == 'L' && boards.islandboards[3].getType() == 'S'){
            State[][] boardstate = new State[15][18];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardstate[i][j] = boards.islandboards[0].getState()[i][j];
                    boardstate[i][j + 9] = boards.islandboards[2].getState()[i][j];
                }
            }

            for(int i = 0; i < 6; i ++){
                for(int j = 0; j < 9; j ++){
                    boardstate[i + 9][j] = boards.islandboards[1].getState()[i][j];
                    boardstate[i + 9][j + 9] = boards.islandboards[3].getState()[i][j];
                }
            }

            state.state = boardstate;
        }

        else if(boards.islandboards[0].getType() == 'L' && boards.islandboards[1].getType() == 'L'){
            State[][] boardstate = new State[18][9];

            for (int i = 0; i < 9; i++) {
                System.arraycopy(boards.islandboards[0].getState()[i], 0, boardstate[i], 0, 9);
            }
            for (int i = 0; i < 9; i++) {
                System.arraycopy(boards.islandboards[1].getState()[i], 0, boardstate[i + 9], 0, 9);
            }

            state.state = boardstate;
        }

        else if(boards.islandboards[0].getType() == 'S' && boards.islandboards[1].getType() == 'S' && boards.islandboards[2].getType() == 'S' && boards.islandboards[3].getType() == 'S'){
            State[][] boardstate = new State[12][18];

            for (int i = 0; i < 6; i++) {
                System.arraycopy(boards.islandboards[0].getState()[i], 0, boardstate[i], 0, 9);
                System.arraycopy(boards.islandboards[1].getState()[i], 0, boardstate[i + 6], 0, 9);
                System.arraycopy(boards.islandboards[2].getState()[i], 0, boardstate[i], 9, 9);
                System.arraycopy(boards.islandboards[3].getState()[i], 0, boardstate[i + 6], 9, 9);
            }

            state.state = boardstate;
        }


        //  fire
        FireCards fires = new FireCards(fire);
        for(Location l: fires.getLocations()){
            int row = l.getRow();
            int col = l.getColumn();
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    state.state[row + i][col + j] = State.FIRE;
                }
            }
        }


        //  cat
        CatCards cats = new CatCards(cat);
        for(CatCard c: cats.catCards){
            int row = c.getLocation().getRow();
            int col = c.getLocation().getColumn();
            int index = 0;

            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    state.state[row + i][col + j] = c.getState()[index];
                    index ++;
                }
            }
        }


        //  raft
        Raft raft_card = new Raft(raft);

        int row = raft_card.getLocation().getRow();
        int col = raft_card.getLocation().getColumn();
        int index = 0;

        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                state.state[row + i][col + j] = raft_card.getState()[index];
                index ++;
            }
        }


        return state.toString();  // FIXME 10
    }


    /**
     * Given a card placement string or a fire tile placement string, check if that placement is valid.
     * <p>
     * A card placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the card is off-board
     * 2. No part of the card is overlapping fire.
     * 3. No part of the card is overlapping a cat.
     * 4. No part of the card is overlapping part of a Raft card (any of the 8 squares surrounding the `o`
     * state)
     * </p>
     * A fire tile placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the fire tile is off-board
     * 2. No part of the fire tile overlaps fire
     * 3. No part of the fire tile overlaps a cat
     * 4. No part of the fire tile overlaps part of a Raft card (any of the 8 squares surrounding the `o` state)
     * 5. The Fire tile is orthogonally adjacent to another fire square.
     * </p>
     *
     * @param gameState       An array representing the gameState
     * @param placementString A string representing a card placement or a fire tile placement
     * @return True if the placement is valid, otherwise false.
     */

    //  Authored by Yuxuan Shi U7789617.
    public static boolean isPlacementValid(String[] gameState, String placementString) {
        BoardState state = new BoardState(gameState[0]);

        int row_limit = state.state.length;
        int col_limit = state.state[0].length;

        // find raft
        int raft_row = 0;
        int raft_col = 0;
        loop:for(int row = 0; row < row_limit; row ++){
            for(int col = 0; col < col_limit; col ++){
                if(state.state[row][col] == State.OBJECTIVE){
                    raft_row = row;
                    raft_col = col;
                    break loop;
                }
            }
        }

        Location[] raft_range = {new Location(raft_row, raft_col),new Location(raft_row - 1,raft_col - 1),
                new Location(raft_row - 1,raft_col), new Location(raft_row - 1,raft_col + 1),
                new Location(raft_row,raft_col - 1), new Location(raft_row,raft_col + 1),
                new Location(raft_row + 1,raft_col - 1), new Location(raft_row + 1,raft_col),
                new Location(raft_row + 1,raft_col + 1)};



        //  card
        if(Character.isLetter(placementString.charAt(1))){
            PathwayCard card = new PathwayCard(placementString.substring(0, 2));
            int row = Integer.parseInt(placementString.substring(2, 4));
            int col = Integer.parseInt(placementString.substring(4, 6));
            Orientation orientation = Orientation.fromChar(placementString.charAt(6));

            card.rotate(orientation);


            for (int i = row; i < row + 3; i++) {
                for (int j = col; j < col + 3; j++) {

                    //  off-board
                    if (i >= row_limit || i < 0 || j >= col_limit || j < 0) {
                        return false;
                    }

                    //  overlap fire or cat
                    if (state.state[i][j] == State.FIRE || state.state[i][j] == State.WILD_CAT
                            || state.state[i][j] == State.GREEN_CAT || state.state[i][j] == State.PURPLE_CAT
                            || state.state[i][j] == State.YELLOW_CAT || state.state[i][j] == State.RED_CAT
                            || state.state[i][j] == State.BLUE_CAT) {
                        return false;
                    }

                    //  overlap raft
                    for (Location location : raft_range) {
                        if (i == location.getRow() && j == location.getColumn()) {
                            return false;
                        }
                    }

                }
            }
        }


        //  fire tile
        else {

            String str = "";
            for (String s : FIRE_TILES) {
                if (s.charAt(0) == placementString.charAt(0)) {
                    str = s;
                }
            }
            FireTile tile = new FireTile(str);

            int row = Integer.parseInt(placementString.substring(1, 3));
            int col = Integer.parseInt(placementString.substring(3, 5));
            Orientation orientation = Orientation.fromChar(placementString.charAt(6));

            if (placementString.charAt(5) == 'T') {
                tile.flip();
            }

            tile.rotate(orientation);


            //  find the top left corner
            int min_row = tile.getLayout()[0].getRow();
            int min_col = tile.getLayout()[0].getColumn();
            for (Location l : tile.getLayout()) {
                if (l.getRow() < min_row) {
                    min_row = l.getRow();
                }
                if (l.getColumn() < min_col) {
                    min_col = l.getColumn();
                }
            }

            //  reposition coordinates
            for (Location l : tile.getLayout()) {
                l.setRow(l.getRow() - min_row);
                l.setColumn(l.getColumn() - min_col);
            }


            for (Location l : tile.getLayout()) {
                int row_project = l.getRow() + row;
                int col_project = l.getColumn() + col;

                //  off-board
                if (row_project >= row_limit || row_project < 0 || col_project >= col_limit || col_project < 0) {
                    return false;
                }

                //  overlap fire or cat
                if (state.state[row_project][col_project] == State.FIRE || state.state[row_project][col_project] == State.WILD_CAT
                        || state.state[row_project][col_project] == State.GREEN_CAT || state.state[row_project][col_project] == State.PURPLE_CAT
                        || state.state[row_project][col_project] == State.YELLOW_CAT || state.state[row_project][col_project] == State.RED_CAT
                        || state.state[row_project][col_project] == State.BLUE_CAT) {
                    return false;
                }

                //  overlap raft
                for (Location location : raft_range) {
                    if (row_project == location.getRow() && col_project == location.getColumn()) {
                        return false;
                    }
                }

            }

            //  check adjacent validity
            boolean validity = false;
            for (Location l : tile.getLayout()) {
                int row_project = l.getRow() + row;
                int col_project = l.getColumn() + col;

                if(row_project - 1 >= 0 & row_project - 1 < row_limit){
                    if(state.state[row_project - 1][col_project] == State.FIRE){
                        validity = true;
                        break;
                    }
                }
                if(row_project + 1 >= 0 && row_project + 1 < row_limit){
                    if(state.state[row_project + 1][col_project] == State.FIRE){
                        validity = true;
                        break;
                    }
                }
                if(col_project - 1 >= 0 && col_project - 1 < col_limit){
                    if(state.state[row_project][col_project - 1] == State.FIRE){
                        validity = true;
                        break;
                    }
                }
                if(col_project + 1 >= 0 && col_project + 1 < col_limit){
                    if(state.state[row_project][col_project + 1] == State.FIRE){
                        validity = true;
                        break;
                    }
                }

            }
            if(!validity){
                return false;
            }
        }


        return true; // FIXME TASK 12
    }


    /**
     * Given a cat movement string, check if the cat movement is valid.
     * <p>
     * A cat movement is valid if:
     * 1. The end location is the same colour as the cat.
     * 2. There is a path from start location to the end location, consisting only of squares the same colour as the
     * cat.
     * 3. The path does not include diagonal movements.
     *
     * @param gameState         An array representing the gameState
     * @param catMovementString A string representing a cat movement.
     * @return True if the cat movement is valid, otherwise false
     */

    //  Authored by Yuxuan Shi U7789617.
    public static boolean isCatMovementValid(String[] gameState, String catMovementString) {

        //  If the cat is exhausted, two cards are needed.
        boolean status = false;
        ExhaustedCats cats = new ExhaustedCats(gameState[3]);
        ExhaustedCat cat = new ExhaustedCat(catMovementString.substring(0,5));
        for(ExhaustedCat c: cats.exhaustedcats){
            if(c.getColour() == cat.getColour()){
                status = true;
            }
        }

        if(status) {
            if (catMovementString.length() != 13) {
                return false;
            }
        }
        else{
            if(catMovementString.length() != 11){
                return false;
            }
        }



        //  whether the card to be discarded exists or not
        Hand hand = new Hand(gameState[2]);
        if(catMovementString.length() == 11){
            boolean result = false;
            PathwayCard card = new PathwayCard(catMovementString.substring(9));

            for(PathwayCard c: hand.getCards_in_hand()){
                if(c.getDeck_ID() == card.getDeck_ID() && c.getID() == card.getID()){
                    result = true;
                }
            }
            if(!result){
                return false;
            }
        }
        else{
            PathwayCard card1 = new PathwayCard(catMovementString.substring(9,11));
            PathwayCard card2 = new PathwayCard(catMovementString.substring(11));

            boolean result1 = false;
            boolean result2 = false;

            for(PathwayCard c: hand.getCards_in_hand()){
                if(c.getDeck_ID() == card1.getDeck_ID() && c.getID() == card1.getID()){
                    result1 = true;
                }

                if(c.getDeck_ID() == card2.getDeck_ID() && c.getID() == card2.getID()){
                    result2 = true;
                }
            }

            if((!result1) || (!result2)){
                return false;
            }
        }


        BoardState board = new BoardState(gameState[0]);

        int row_start = Integer.parseInt(catMovementString.substring(1,3));
        int col_start = Integer.parseInt(catMovementString.substring(3,5));
        int row_end = Integer.parseInt(catMovementString.substring(5,7));
        int col_end = Integer.parseInt(catMovementString.substring(7,9));

        //  out of boundary
        int row_limit = board.state.length;
        int col_limit = board.state[0].length;
        if(row_end > row_limit || col_end > col_limit){
            return false;
        }


        //  The end location is the same colour as the cat.
        char end = board.state[row_end][col_end].toChar();
        if(end != Character.toLowerCase(catMovementString.charAt(0)) && end != 'w'){
            return false;
        }


        //  There is a path
        if(PathSearch.pathExist(board, row_start, col_start, row_end, col_end)){
            return true;
        }


        return false; // FIXME TASK 14
    }


    /**
     * Determine whether the game is over. The game ends if any of the following conditions are met:
     * <p>
     * Fire tile placement:
     * 1. If this placement action is not valid AND there is no other position this tile could be placed validly
     * (the game is lost).
     * 2. If placing this fire tile makes it impossible for any one cat to reach the raft (the game is lost).
     * <p>
     * Cat movement:
     * 1. If after moving this cat, all cats have safely reached the raft (the game is won).
     * <p>
     * Card placement:
     * 1. If after placing this card, there are no more fire tiles left in the bag (the game is lost).
     * </p>
     *
     * @param gameState An array of strings representing the game state
     * @param action    A string representing a fire tile placement, cat movement or card placement action.
     * @return True if the game is over (regardless of whether it is won or lost), otherwise False.
     */

    //  Authored by Yuxuan Shi U7789617.
    public static boolean isGameOver(String[] gameState, String action) {
        BoardState state = new BoardState(gameState[0]);
        FireTileBag bag = new FireTileBag(gameState[4]);

        // find raft
        int row_limit = state.state.length;
        int col_limit = state.state[0].length;
        int raft_row = 0;
        int raft_col = 0;
        loop:for(int row = 0; row < row_limit; row ++){
            for(int col = 0; col < col_limit; col ++){
                if(state.state[row][col] == State.OBJECTIVE){
                    raft_row = row;
                    raft_col = col;
                    break loop;
                }
            }
        }

        Location[] raft_range = {new Location(raft_row, raft_col),new Location(raft_row - 1,raft_col - 1),
                new Location(raft_row - 1,raft_col), new Location(raft_row - 1,raft_col + 1),
                new Location(raft_row,raft_col - 1), new Location(raft_row,raft_col + 1),
                new Location(raft_row + 1,raft_col - 1), new Location(raft_row + 1,raft_col),
                new Location(raft_row + 1,raft_col + 1)};



        //  after placing this card there are no fire tile available
        if(Character.isLetter(action.charAt(1))){
            if(isPlacementValid(gameState, action)){
                if(bag.getBag() == null){
                    return true;
                }
            }
        }


        //  all cats have reached
        if(action.length() >= 9){
            if(isCatMovementValid(gameState, action)){
                BoardState state1 = new BoardState(moveCat(gameState,action)[0]);

                boolean result = true;
                loop:for(int i = 0; i < row_limit; i ++){
                    for(int j = 0; j < col_limit; j ++){
                        if(state1.state[i][j] == State.WILD_CAT || state1.state[i][j] == State.GREEN_CAT
                                || state1.state[i][j] == State.PURPLE_CAT || state1.state[i][j] == State.YELLOW_CAT
                                || state1.state[i][j] == State.RED_CAT || state1.state[i][j] == State.BLUE_CAT){

                            for(int index = 0; index < 9; index ++){
                                if(i == raft_range[index].getRow() && j == raft_range[index].getColumn()){
                                    break;
                                }

                                if(index == 8){
                                    result = false;
                                    break loop;
                                }
                            }
                        }
                    }
                }
                if(result){
                    return true;
                }
            }
        }


        //  fire tile check
        if(action.length() == 7 && Character.isDigit(action.charAt(1))) {

            //  If this file tile placement action is not valid AND there is no other position this tile could be placed validly
            //  can flip or rotate to fit in
            if (!isPlacementValid(gameState, action)) {
                boolean result = true;
                for (int i = 0; i < row_limit; i++) {
                    for (int j = 0; j < col_limit; j++) {

                        String row = "";
                        String col = "";

                        if (i < 10) {
                            row = "0" + i;
                        } else {
                            row += i;
                        }
                        if (j < 10) {
                            col = "0" + j;
                        } else {
                            col += j;
                        }

                        String[] choice = {"TN", "TS", "TW", "TE", "FS", "FW", "FE", "FN"};
                        for(String s: choice) {
                            String placement = action.charAt(0) + row + col + s;
                            if (isPlacementValid(gameState, placement)) {
                                result = false;
                                break;
                            }
                        }
                    }
                }
                if (result) {
                    return true;
                }
            }


            //  If placing this fire tile makes it impossible for any one cat to reach the raft (the game is lost).
            else {
                BoardState state1 = new BoardState(applyPlacement(gameState, action)[0]);

                for (int i = 0; i < row_limit; i++) {
                    loop:for (int j = 0; j < col_limit; j++) {
                        if (state1.state[i][j] == State.WILD_CAT || state1.state[i][j] == State.GREEN_CAT
                                || state1.state[i][j] == State.PURPLE_CAT || state1.state[i][j] == State.YELLOW_CAT
                                || state1.state[i][j] == State.RED_CAT || state1.state[i][j] == State.BLUE_CAT) {

                            //  if this cat is on the raft, don't need to check
                            for (int index = 0; index < 9; index++) {
                                if (i == raft_range[index].getRow() && j == raft_range[index].getColumn()) {
                                    continue loop;
                                }
                            }


                            //  check whether is impossible for this cat to reach the raft
                            int start_row = i;
                            int start_col = j;
                            for(int index = 0; index < 9; index ++){
                                int end_row = raft_range[index].getRow();
                                int end_col = raft_range[index].getColumn();

                                if(Character.toLowerCase(state1.state[i][j].toChar()) == state1.state[end_row][end_col].toChar()
                                || state1.state[end_row][end_col].toChar() =='w') {
                                    if (PathSearch.potential_pathExist(state1, start_row, start_col, end_row, end_col)) {
                                        continue loop;
                                    }
                                }

                                if(index == 8){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }


        //  The game fails when the flames leave a passage of only one square width.
        //  Don't know how to solve this situation.

        return false;     // FIXME TASK 15
    }
}


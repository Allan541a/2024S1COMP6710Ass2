package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.scene.effect.DropShadow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//  Co-authored by the team. Yuxuan Shi U7789617, James Engeman U7484227


public class Game extends Application {
    private static final int TILE_WIDTH = 40;
    private static final int TILE_HEIGHT = 40;
    private static final int MARGIN_X = 20;

    //  An array of strings that monitor the progress of the entire game.
    private String[] gameState = {"", "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
            "ABCD", "", "abcdefghijklmnopqrstuvwxyzABCDE"};

    //  The URL of the image.
    private static HashMap<State, String> stateImageMap = new HashMap<>();
    static {
        stateImageMap.put(State.BLUE, "comp1110/ass2/gui/assets/blue.png");
        stateImageMap.put(State.BLUE_CAT, "comp1110/ass2/gui/assets/blueCat.png");
        stateImageMap.put(State.FIRE, "comp1110/ass2/gui/assets/fire.png");
        stateImageMap.put(State.GREEN, "comp1110/ass2/gui/assets/green.png");
        stateImageMap.put(State.GREEN_CAT, "comp1110/ass2/gui/assets/greenCat.png");
        stateImageMap.put(State.PURPLE, "comp1110/ass2/gui/assets/purple.png");
        stateImageMap.put(State.PURPLE_CAT, "comp1110/ass2/gui/assets/purpleCat.png");
        stateImageMap.put(State.RED, "comp1110/ass2/gui/assets/red.png");
        stateImageMap.put(State.RED_CAT, "comp1110/ass2/gui/assets/redCat.png");
        stateImageMap.put(State.YELLOW, "comp1110/ass2/gui/assets/yellow.png");
        stateImageMap.put(State.YELLOW_CAT, "comp1110/ass2/gui/assets/yellowCat.png");
        stateImageMap.put(State.OBJECTIVE, "comp1110/ass2/gui/assets/objective.png");
        stateImageMap.put(State.WILD, "comp1110/ass2/gui/assets/objective.png");
        stateImageMap.put(State.WILD_CAT, "comp1110/ass2/gui/assets/objective.png");
    }

    //  String to monitor firetile.
    private String fireTile = "";

    private Group root;

    //  Default challenge difficulty is 0.
    private int difficultyLevel = 0;

    //  The number of cards that have been drawn this round.
    private int cardsDrawn = 0;

    //  The order in which the cards are drawn in this round, which is used to generate the draw request.
    private StringBuilder cardSequence = new StringBuilder();

    private Label cardsDrawnLabel;

    private Label FiretileLabel;

    //  The number of cards remaining in the four piles, initially 25.
    private int[] remainingCards = {25, 25, 25, 25};


    private final Group placement_logic = new Group();

    private static final String PLACEMENT_LOGIC = """
            This code uses strings to select and place pathway cards, fire tile cards and cat tiles. This is done primarily through their coordinates,
            each coordinate must be two digits in length, so when the row is 3, it will be written as 03. Since each process is done through string inputs, 
            each placement will done through the top left of each tile, meaning that the top left corner of the pathway card and fire tile will be placed on the 
            designated coordinate, and the remaining tiles will accompany to the right and below that placement. 
            
            The string inputs for each action are provided below:
            Pathway Card Placement String: {Deck}{ID}{row}{column}{orientation}
            Fire Tile Placement String: {ID}{row}{column}{flipped}{orientation}
            Cat Movement String: {colour}{startLocation}{endLocation}{discardedCards}
            
            In terms of placement of the Pathway card, the deck and ID are labelled right next to each, in terms of coordinates, the row will go first with the column proceeding.
            Since this card can be rotated the desired orientation will be provided last, the options being N, E, S, W
            
            In terms of placement of the Fire Tile cards, it is practically the same as Pathway cards, except that fire tiles can be flipped, this represented through a bool, 
            being either True for it being flipped represented as a "T", or False to it being flipped represented through a "F".
            
            Cat Movement is more straightforward, its ID represented through a capital of the cats letter, for example the blue cat's ID is a "B". Additionally, as per the rules, 
            when the cat is moved a pathway card is discarded, so the appropriate pathway cards Deck and ID must be stated. Moreover, if the cat is exhausted and then moved, 
            then two pathway cards must be discarded.  
            

            """;
    private MediaPlayer bgmPlayer;
    private MediaPlayer catPlayer;


    @Override
    public void start(Stage primaryStage) throws URISyntaxException {

        root = new Group();

        initializeGame();

        Scene scene = new Scene(root, 1100, 880);

        primaryStage.setScene(scene);
        primaryStage.setTitle("RaceToTheRaft");
        primaryStage.show();

        root.getChildren().add(placement_logic);

        //  music
        Media sound = new Media(getClass().getResource("/comp1110/ass2/gui/assets/001.mp3").toURI().toString());
        bgmPlayer = new MediaPlayer(sound);
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        bgmPlayer.play();
    }


    // Initialise the game interface.
    private void initializeGame(){

        addShadow(gameState);

        //  Select Difficulty.
        HBox difficultyBox = new HBox(10);
        Label difficultyLabel = new Label("choose difficulty:");
        difficultyBox.getChildren().add(difficultyLabel);
        for (int i = 0; i <= 5; i++) {
            Button difficultyButton = new Button(String.valueOf(i));
            int level = i; // 为了Lambda表达式能够访问到i，需要将其复制给一个final变量
            difficultyButton.setOnAction(event -> setDifficulty(level));
            difficultyBox.getChildren().add(difficultyButton);
        }

        //  "Reset" button.
        Button resetButton = new Button("Restart");
        resetButton.setOnAction(event -> {
            try {
                resetGame();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });


        difficultyBox.setLayoutX(10);
        difficultyBox.setLayoutY(10);
        resetButton.setLayoutX(10);
        resetButton.setLayoutY(30);

        root.getChildren().addAll(difficultyBox, resetButton);


        // Card Draw Button.
        HBox drawCardsBox = new HBox(10);
        drawCardsBox.setLayoutX(10);
        drawCardsBox.setLayoutY(70);

        Button drawA = new Button("A: " + remainingCards[0] + " left");
        drawA.setId("drawA");
        drawA.setOnAction(event -> drawCard("A"));
        Button drawB = new Button("B: " + remainingCards[1] + " left");
        drawB.setId("drawB");
        drawB.setOnAction(event -> drawCard("B"));
        Button drawC = new Button("C: " + remainingCards[2] + " left");
        drawC.setId("drawC");
        drawC.setOnAction(event -> drawCard("C"));
        Button drawD = new Button("D: " + remainingCards[3] + " left");
        drawD.setId("drawD");
        drawD.setOnAction(event -> drawCard("D"));

        drawCardsBox.getChildren().addAll(drawA, drawB, drawC, drawD);
        root.getChildren().add(drawCardsBox);


        // Text of the number of cards that have been drawn this round.
        cardsDrawnLabel = new Label("Already drew: " + cardsDrawn);
        cardsDrawnLabel.setLayoutX(10);
        cardsDrawnLabel.setLayoutY(100);
        root.getChildren().add(cardsDrawnLabel);


        //  Text of the number of fire tiles in the bag.
        FiretileLabel = new Label("Firetile: " + gameState[4].length());
        FiretileLabel.setLayoutX(200);
        FiretileLabel.setLayoutY(100);
        root.getChildren().add(FiretileLabel);

        //  Draw the board.
        displayBoard(difficultyLevel);


        //  Text box for receiving commands.
        TextField commandInput = new TextField();
        commandInput.setPromptText("Enter command...");

        //  Rotate or flip the card or fire tile, to provide player with a preview.
        Button redrawCardsButton = new Button("Rotate or Flip");
        redrawCardsButton.setOnAction(event -> {
            String command = commandInput.getText();

            //  Card placement command.
            if(Character.isLetter(command.charAt(1))){showRotatedCards(command);}

            //  Fire tile placement command.
            else if(command.length() == 7 && Character.isDigit(command.charAt(1))){rotate_and_flipFiretile(command);}
        });


        //  Submit the command.
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            String command = commandInput.getText();

            //  Card placement command.
            if(Character.isLetter(command.charAt(1))){
                PathwayCardPlacement(command);}

            //  Fire tile placement command.
            else if(command.length() == 7 && Character.isDigit(command.charAt(1))){
                try {
                    FiretilePlacement(command);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }

            //  Cat movement command.
            else{
                try {
                    handleCatMovement(command);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
            commandInput.clear();
        });


        Button InstructionButton = new Button("Instructions");
        InstructionButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Instructions");
            alert.setTitle("Instructions");
            alert.setContentText(PLACEMENT_LOGIC);
            alert.show();
        });
        InstructionButton.setText("Instructions");

        commandInput.setLayoutX(10);
        commandInput.setLayoutY(530);
        redrawCardsButton.setLayoutX(10);
        redrawCardsButton.setLayoutY(560);
        submitButton.setLayoutX(110);
        submitButton.setLayoutY(560);
        InstructionButton.setLayoutX(175);
        InstructionButton.setLayoutY(560);

        root.getChildren().addAll(commandInput, redrawCardsButton, submitButton,InstructionButton);


        ImageView imageView = new ImageView();
        Image image = new Image("comp1110/ass2/gui/assets/GameImage.png", 200, 200, false, false);
        imageView.setImage(image);
        imageView.setX(900);
        imageView.setY(650);

        root.getChildren().add(imageView);

        addShadow(gameState);

    }


    //  Select the difficulty. Associated with the Difficulty button.
    private void setDifficulty(int level) {
        difficultyLevel = level;
    }


    //  Reset the game.
    private void resetGame() throws URISyntaxException {

        //  Clear all nodes.

        root.getChildren().clear();


            //  Initialise all variables used to monitor the progress of the game.
        gameState[1] = "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy";
        gameState[2] = "ABCD";
        gameState[3] = "";
        gameState[4] = "abcdefghijklmnopqrstuvwxyzABCDE";
        cardsDrawn = 0;
        cardSequence.setLength(0);
        Arrays.fill(remainingCards, 25);
        fireTile = "";

        initializeGame();
    }


    //  Draw a card from the designated deck.
    private void drawCard(String pile) {
        int pileIndex = pile.charAt(0) - 'A';

        /*
        Cards can only be drawn when the remaining number in the card pile is greater than 0
        and the number of cards in the hand is less than 6.
         */
        if (remainingCards[pileIndex] > 0 && cardsDrawn < 6) {
            remainingCards[pileIndex]--;
            cardsDrawn++;

            //  Record the order in which cards are drawn.
            cardSequence.append(pile);
            cardsDrawnLabel.setText("Already drew: " + cardsDrawn);
            updateButtons();
        }

        //  When six cards are drawn, the hand cards are displayed.
        if (cardsDrawn == 6) {
            String sequence = cardSequence.toString();
            int count_A = 0;
            int count_B = 0;
            int count_C = 0;
            int count_D = 0;
            for(char s: sequence.toCharArray()){
                switch (s){
                    case 'A': count_A ++;break;
                    case 'B': count_B ++;break;
                    case 'C': count_C ++;break;
                    case 'D': count_D ++;break;
                }
            }
            String drawRequest = "";
            if (count_A > 0){drawRequest += "A" + count_A;}
            if (count_B > 0){drawRequest += "B" + count_B;}
            if (count_C > 0){drawRequest += "C" + count_C;}
            if (count_D > 0){drawRequest += "D" + count_D;}
            displayCards(Arrays.copyOf(gameState, gameState.length), drawRequest);
            cardSequence = new StringBuilder();
        }
    }


    //  Refresh the draw button.
    private void updateButtons() {
        for (int i = 0; i < remainingCards.length; i++) {
            char pile = (char) ('A' + i);
            String buttonText = pile + ": " + remainingCards[i] + " left";
            Button button = (Button) root.lookup("#draw" + pile);
            button.setText(buttonText);

            //  When the number of remaining cards is 0, the button is disabled.
            button.setDisable(remainingCards[i] == 0);
        }
    }


    //  Draw the board according to the selected difficulty.
    private void displayBoard(int difficulty) {
        String challenge = RaceToTheRaft.chooseChallenge(difficulty);
        String boardState = RaceToTheRaft.initialiseChallenge(challenge);
        gameState[0] = boardState;
        root.getChildren().removeIf(node -> node instanceof ImageView);
        BoardState s = new BoardState(boardState);
        State[][] state = s.getState();
        int numRows = state.length;
        int numCols = state[0].length;

        // Left coordinate.
        for (int row = numRows; row > 0; row--) {
            Text text = new Text(Integer.toString(row - 1));
            text.setX(MARGIN_X - 20 + 350); // 根据需要调整位置
            text.setY(row * TILE_HEIGHT - 15); // 根据需要调整位置
            root.getChildren().add(text);
        }

        // Lower coordinates.
        for (int col = numCols; col > 0; col--) {
            Text text = new Text(Integer.toString(col - 1));
            text.setX((col - 1) * TILE_WIDTH + MARGIN_X + 350 + 15); // 根据需要调整位置
            text.setY(numRows * TILE_HEIGHT + 20); // 根据需要调整位置
            root.getChildren().add(text);
        }

        // Draw chessboard.
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                State indexState = state[row][col];
                ImageView imageView = new ImageView();
                String imagePath = stateImageMap.get(indexState);
                imageView.setImage(new Image(imagePath, 40, 40, false, false));
                imageView.setX(MARGIN_X + col * TILE_WIDTH + 350);
                imageView.setY(row * TILE_HEIGHT);
                root.getChildren().add(imageView);
            }
        }
    }



    //  According to the card drawing request, refresh the game state parameters. Draw the hand cards.
    private void displayCards(String[] gameState, String drawRequest) {

        String[] updatedGameState = RaceToTheRaft.drawHand(gameState, drawRequest);
        this.gameState[1] = updatedGameState[1];
        this.gameState[2] = updatedGameState[2];

        //  Display hand cards.
        final int cardSpacingVertical = TILE_HEIGHT / 3;
        final int cardsPerColumn = 3;
        final int columnWidth = TILE_WIDTH * 4;
        Hand hands = new Hand(this.gameState[2]);
        PathwayCard[] cards = hands.getCards_in_hand();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) continue;
            int n = switch (i) {
                case 0 -> 0;
                case 1 -> 3;
                case 2 -> 1;
                case 3 -> 4;
                case 4 -> 2;
                case 5 -> 5;
                default -> -1;
            };
            int column = n / cardsPerColumn;
            int offY = (n % cardsPerColumn) * (TILE_HEIGHT * 3 + cardSpacingVertical) + 120;
            int offX = MARGIN_X + (column % 2) * columnWidth;
            State[] states = cards[i].getState();


            Group cardGroup = new Group();
            cardGroup.prefWidth(TILE_WIDTH * 3);
            cardGroup.prefHeight(TILE_HEIGHT * 3);

            // Set ID for each card
            String cardId = "" + cards[i].getDeck_ID() + cards[i].getID() + "-" + cards[i].getOrientation().toChar();
            cardGroup.setId(cardId);

            for (int j = 0; j < 9; j++) {
                ImageView imageViewHand = new ImageView();
                String imagePath = stateImageMap.get(states[j]);

                imageViewHand.setImage(new Image(imagePath, TILE_WIDTH, TILE_HEIGHT, false, false));

                // Set the position of each image view within the card group
                switch (j) {
                    case 0 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY);}
                    case 1 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY);}
                    case 2 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY);}
                    case 3 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 4 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 5 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 6 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 7 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 8 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                }

                // Add the image view to the card group
                cardGroup.getChildren().add(imageViewHand);
            }

            // Add text displaying the card ID below the card
            Text idText = new Text(cardId);
            idText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            idText.setFill(Color.BLACK);
            idText.setX(MARGIN_X + offX + TILE_WIDTH + 10);
            idText.setY(offY + TILE_HEIGHT * 3 + 10);

            // Add the card group and the ID text to the root
            root.getChildren().addAll(cardGroup, idText);
        }
    }


    //  According to the command, place the pathway card.
    private void PathwayCardPlacement(String command) {
        if (fireTile != "") {
            // have to place fire tile first
            Alert gameOverAlert = new Alert(Alert.AlertType.WARNING);
            gameOverAlert.setTitle("Invalid movement");
            gameOverAlert.setContentText("You have to place this fire tile before placing another pathway card.");
            gameOverAlert.showAndWait();
            return;
        }

        if (RaceToTheRaft.isGameOver(gameState, command)) {
            Alert gameOverAlert = new Alert(Alert.AlertType.WARNING);
            gameOverAlert.setTitle("You have lost the game");
            gameOverAlert.setContentText("After placing this card, there are no more fire tiles left in the bag.");
            gameOverAlert.showAndWait();
            return;
        }

        if (!RaceToTheRaft.isPlacementValid(gameState, command)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Card Placement");
            alert.setHeaderText(null);
            alert.setContentText("""
                    1. No part of the card is off-board.
                    2. No part of the card is overlapping fire.
                    3. No part of the card is overlapping a cat.
                    4. No part of the card is overlapping part of a Raft card""");
            alert.showAndWait();
            return;
        }

        //  Place pathway card, refresh the board and hand cards.
        gameState = RaceToTheRaft.applyPlacement(gameState, command);
        root.getChildren().removeIf(node -> node instanceof ImageView || node instanceof Text || node instanceof Group);
        BoardState s = new BoardState(gameState[0]);
        State[][] state = s.getState();
        int numRows = state.length;
        int numCols = state[0].length;

        // Left coordinates.
        for (int row = numRows; row > 0; row--) {
            Text text = new Text(Integer.toString(row - 1));
            text.setX(MARGIN_X - 20 + 350);
            text.setY(row * TILE_HEIGHT - 15);
            root.getChildren().add(text);
        }

        // Lower coordinates
        for (int col = numCols; col > 0; col--) {
            Text text = new Text(Integer.toString(col - 1));
            text.setX((col - 1) * TILE_WIDTH + MARGIN_X + 350 + 15);
            text.setY(numRows * TILE_HEIGHT + 20);
            root.getChildren().add(text);
        }

        //  Redraw the board.
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                State indexState = state[row][col];
                ImageView imageView = new ImageView();
                String imagePath = stateImageMap.get(indexState);
                imageView.setImage(new Image(imagePath, 40, 40, false, false));
                imageView.setX(MARGIN_X + col * TILE_WIDTH + 350);
                imageView.setY(row * TILE_HEIGHT);
                root.getChildren().add(imageView);
            }
        }

        //  Redraw hand cards, the hand string in gameState has been updated.
        final int cardSpacingVertical = TILE_HEIGHT / 3;
        final int cardsPerColumn = 3;
        final int columnWidth = TILE_WIDTH * 4;
        Hand hands = new Hand(gameState[2]);
        PathwayCard[] cards = hands.getCards_in_hand();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) continue;
            int n = switch (i) {
                case 0 -> 0;
                case 1 -> 3;
                case 2 -> 1;
                case 3 -> 4;
                case 4 -> 2;
                case 5 -> 5;
                default -> -1;
            };
            int column = n / cardsPerColumn;
            int offY = (n % cardsPerColumn) * (TILE_HEIGHT * 3 + cardSpacingVertical) + 120;
            int offX = MARGIN_X + (column % 2) * columnWidth;
            State[] states = cards[i].getState();

            Group cardGroup = new Group();
            cardGroup.prefWidth(TILE_WIDTH * 3);
            cardGroup.prefHeight(TILE_HEIGHT * 3);

            // Set ID for each card
            String cardId = "" + cards[i].getDeck_ID() + cards[i].getID() + "-" + cards[i].getOrientation().toChar();
            cardGroup.setId(cardId);

            for (int j = 0; j < 9; j++) {
                ImageView imageViewHand = new ImageView();
                String imagePath = stateImageMap.get(states[j]);

                imageViewHand.setImage(new Image(imagePath, TILE_WIDTH, TILE_HEIGHT, false, false));

                // Set the position of each image view within the card group
                switch (j) {
                    case 0 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY);}
                    case 1 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY);}
                    case 2 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY);}
                    case 3 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 4 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 5 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 6 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 7 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 8 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}}

                // Add the image view to the card group
                cardGroup.getChildren().add(imageViewHand);
            }

            // Add text displaying the card ID below the card
            Text idText = new Text(cardId);
            idText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            idText.setFill(Color.BLACK);
            idText.setX(MARGIN_X + offX + TILE_WIDTH + 10);
            idText.setY(offY + TILE_HEIGHT * 3 + 10);
            // Add the card group and the ID text to the root
            root.getChildren().addAll(cardGroup, idText);

        }

        //  After placing the pathway card, a random fire tile is displayed.
        fireTile = RaceToTheRaft.drawFireTile(gameState);
        FireTile tile = new FireTile(fireTile);
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

        displayFireTile(tile.getLayout(), fireTile.charAt(0), 'F', 'N');

        //  If you run out of hand cards after placing this card, enter the next round.
        if(gameState[2].equals("ABCD")){
            gameState[3] = "";
            cardsDrawn = 0;
            cardsDrawnLabel.setText("Already drew: " + cardsDrawn);
            addShadow(gameState);
        }


        ImageView imageView = new ImageView();
        Image image = new Image("comp1110/ass2/gui/assets/GameImage.png", 200, 200, false, false);
        imageView.setImage(image);
        imageView.setX(900);
        imageView.setY(650);

        root.getChildren().add(imageView);

        addShadow(gameState);

        FiretileLabel.setText("Firetile: " + gameState[4].length());
    }


    //  To preview the rotated card, use the same commands as for placement.
    private void showRotatedCards(String command) {
        root.getChildren().removeIf(node -> (node instanceof Text && node.getBoundsInParent().getMinX() < 320) || node instanceof Group);

        char Deck_ID = command.charAt(0);
        char ID = command.charAt(1);
        char orientation = command.charAt(6);

        //  redraw hand cards, the one in the command should be rotated
        final int cardSpacingVertical = TILE_HEIGHT / 3;
        final int cardsPerColumn = 3;
        final int columnWidth = TILE_WIDTH * 4;
        Hand hands = new Hand(gameState[2]);
        PathwayCard[] cards = hands.getCards_in_hand();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) continue;
            int n = switch (i) {
                case 0 -> 0;
                case 1 -> 3;
                case 2 -> 1;
                case 3 -> 4;
                case 4 -> 2;
                case 5 -> 5;
                default -> -1;
            };
            int column = n / cardsPerColumn;
            int offY = (n % cardsPerColumn) * (TILE_HEIGHT * 3 + cardSpacingVertical) + 120;
            int offX = MARGIN_X + (column % 2) * columnWidth;
            if(cards[i].getDeck_ID() == Deck_ID && cards[i].getID() == ID){
                cards[i].rotate(Orientation.fromChar(orientation));
                cards[i].setOrientation(Orientation.fromChar(orientation));
            }
            State[] states = cards[i].getState();

            Group cardGroup = new Group();
            cardGroup.prefWidth(TILE_WIDTH * 3);
            cardGroup.prefHeight(TILE_HEIGHT * 3);

            String cardId = "" + cards[i].getDeck_ID() + cards[i].getID() + "-" + cards[i].getOrientation().toChar();
            cardGroup.setId(cardId);

            for (int j = 0; j < 9; j++) {
                ImageView imageViewHand = new ImageView();
                String imagePath = stateImageMap.get(states[j]);

                imageViewHand.setImage(new Image(imagePath, TILE_WIDTH, TILE_HEIGHT, false, false));

                // Set the position of each image view within the card group
                switch (j) {
                    case 0 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY);}
                    case 1 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY);}
                    case 2 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY);}
                    case 3 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 4 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 5 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 6 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 7 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 8 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                }

                // Add the image view to the card group
                cardGroup.getChildren().add(imageViewHand);
            }

            // Add text displaying the card ID below the card
            Text idText = new Text(cardId);
            idText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            idText.setFill(Color.BLACK);
            idText.setX(MARGIN_X + offX + TILE_WIDTH + 10);
            idText.setY(offY + TILE_HEIGHT * 3 + 10);

            // Add the card group and the ID text to the root
            root.getChildren().addAll(cardGroup, idText);
        }
    }


    //  After placing the path tile, reveal a random fire tile.
    private void displayFireTile(Location[] layout, char ID, char flip, char orientation) {

        root.getChildren().removeIf(node -> {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;

                double x = imageView.getX();
                double y = imageView.getY();

                if (x >= MARGIN_X + 350 && y >= 640  && x <= MARGIN_X + 350 + 250) {
                    return true;
                }
            }
            else if (node instanceof Text) {
                Text text = (Text) node;

                double textY = text.getY();

                if (textY <= 640 && textY >= 630) {
                    return true;
                }
            }
            return false;
        });

        int index = 0;

        for (Location coordinate : layout) {
            int row = coordinate.getRow();
            int col = coordinate.getColumn();

            double x = MARGIN_X + col * TILE_WIDTH + 350;
            double y = row * TILE_HEIGHT + 680;

            ImageView imageView = new ImageView();
            imageView.setImage(new Image("comp1110/ass2/gui/assets/fire.png", TILE_WIDTH, TILE_HEIGHT, false, false));

            imageView.setX(x);
            imageView.setY(y);

            //  Add text information above the box in the upper left corner.
            if (index == 0) {

                Text text = new Text(ID + "-" + flip + "-" + orientation);
                text.setX(x);
                text.setY(y - 10);

                root.getChildren().addAll(imageView, text);
                index = 1;
            } else {

                root.getChildren().add(imageView);
            }
        }

    }



    //  Preview the fire tile after rotation or flipping. Same as the placed command.
    private void rotate_and_flipFiretile(String command){
        char ID = command.charAt(0);
        char flip = command.charAt(5);
        char o = command.charAt(6);
        Orientation orientation = Orientation.fromChar(command.charAt(6));

        FireTile fireTile1 = new FireTile(fireTile);
        if(flip == 'T'){
            fireTile1.flip();
        }

        fireTile1.rotate(orientation);

        //  reposition coordinates
        int min_row = fireTile1.getLayout()[0].getRow();
        int min_col = fireTile1.getLayout()[0].getColumn();
        for(Location l: fireTile1.getLayout()){
            if(l.getRow() < min_row){
                min_row = l.getRow();
            }
            if(l.getColumn() < min_col){
                min_col = l.getColumn();
            }
        }

        for(Location l: fireTile1.getLayout()){
            l.setRow(l.getRow() - min_row);
            l.setColumn(l.getColumn() - min_col);
        }

        root.getChildren().removeIf(node -> {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;

                double x = imageView.getX();
                double y = imageView.getY();

                if (x >= MARGIN_X + 350 && y >= 640 && x <= MARGIN_X + 350 + 250) {
                    return true;
                }
            }
            else if (node instanceof Text) {
                Text text = (Text) node;

                double textX = text.getX();

                if (textX >= MARGIN_X + 350) {
                    return true;
                }
            }
            return false;
        });


        int index = 0;

        for (Location coordinate : fireTile1.getLayout()) {
            int row = coordinate.getRow();
            int col = coordinate.getColumn();

            double x = MARGIN_X + col * TILE_WIDTH + 350;
            double y = row * TILE_HEIGHT + 680;

            ImageView imageView = new ImageView();
            imageView.setImage(new Image("comp1110/ass2/gui/assets/fire.png", TILE_WIDTH, TILE_HEIGHT, false, false));

            imageView.setX(x);
            imageView.setY(y);

            //  Add text information above the box in the upper left corner.
            if (index == 0) {

                Text text = new Text(ID + "-" + flip + "-" + o);
                text.setX(x);
                text.setY(y - 10);

                root.getChildren().addAll(imageView, text);
                index = 1;
            } else {

                root.getChildren().add(imageView);
            }
        }
    }


    //  Place a fire tile. Refresh the board and erase the fire tile.
    private void FiretilePlacement(String command) throws URISyntaxException {
        if (RaceToTheRaft.isGameOver(gameState, command)) {

            Alert gameOverAlert = new Alert(Alert.AlertType.WARNING);
            gameOverAlert.setTitle("You have lost the game");
            gameOverAlert.setContentText("""
                   1. This placement action is not valid AND there is no other position this tile could be placed validly.
                   2. Placing this fire tile makes it impossible for any one cat to reach the raft.""");
            gameOverAlert.showAndWait();
            return;
        }


        if (!RaceToTheRaft.isPlacementValid(gameState, command)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Fire tile Placement");
            alert.setContentText("""
                    1. No part of the fire tile is off-board.
                    2. No part of the fire tile overlaps fire.
                    3. No part of the fire tile overlaps a cat.
                    4. No part of the fire tile overlaps part of a Raft card.
                    5. The Fire tile is orthogonally adjacent to another fire square.""");
            alert.showAndWait();
            return;
        }

        gameState = RaceToTheRaft.applyPlacement(gameState, command);

        //  remove this fire tile from bag
        char ID = command.charAt(0);
        String bag = gameState[4];
        String removed_bag = bag.replace(String.valueOf(ID), "");
        gameState[4] = removed_bag;


        root.getChildren().removeIf(node -> {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;

                double x = imageView.getX();
                double y = imageView.getY();

                if ((x >= MARGIN_X + 350 && y <= 620) || (x >= MARGIN_X + 350 && x <= MARGIN_X + 350 + 220)) {
                    return true;
                }
            }
            else if (node instanceof Text) {
                Text text = (Text) node;

                double textX = text.getX();

                if (textX >= MARGIN_X + 350) {
                    return true;
                }
            }
            return false;
        });


        //  Redraw board.
        BoardState s = new BoardState(gameState[0]);
        State[][] state = s.getState();
        int numRows = state.length;
        int numCols = state[0].length;


        for (int row = numRows; row > 0; row--) {
            Text text = new Text(Integer.toString(row - 1));
            text.setX(MARGIN_X - 20 + 350);
            text.setY(row * TILE_HEIGHT - 15);
            root.getChildren().add(text);
        }


        for (int col = numCols; col > 0; col--) {
            Text text = new Text(Integer.toString(col - 1));
            text.setX((col - 1) * TILE_WIDTH + MARGIN_X + 350 + 15);
            text.setY(numRows * TILE_HEIGHT + 20);
            root.getChildren().add(text);
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                State indexState = state[row][col];
                ImageView imageView = new ImageView();
                String imagePath = stateImageMap.get(indexState);
                imageView.setImage(new Image(imagePath, 40, 40, false, false));
                imageView.setX(MARGIN_X + col * TILE_WIDTH + 350);
                imageView.setY(row * TILE_HEIGHT);
                root.getChildren().add(imageView);
            }
        }

        fireTile = "";

        addShadow(gameState);
        FiretileLabel.setText("Firetile: " + gameState[4].length());

    }


    //  Move cat and discard hand cards.
    private void handleCatMovement(String command) throws URISyntaxException {

        boolean over = false;

        if (fireTile != "") {
            // have to place fire tile first
            Alert gameOverAlert = new Alert(Alert.AlertType.WARNING);
            gameOverAlert.setTitle("Invalid movement");
            gameOverAlert.setContentText("You have to place this fire tile before moving a cat.");
            gameOverAlert.showAndWait();
            return;
        }

        if (RaceToTheRaft.isGameOver(gameState, command)) {
            over = true;
        }


        if (!RaceToTheRaft.isCatMovementValid(gameState, command)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Cat Movement");
            alert.setContentText("""
                    1. The end location is the same colour as the cat.
                    2. There is a path from start location to the end location, consisting only of squares the same colour as the cat.
                    3. The path does not include diagonal movements.
                    4. You have to discard two cards to move an exhausted cat.""");
            alert.showAndWait();
            return;
        }

        gameState = RaceToTheRaft.moveCat(gameState, command);

        root.getChildren().removeIf(node -> node instanceof ImageView || node instanceof Text || node instanceof Group);
        BoardState s = new BoardState(gameState[0]);
        State[][] state = s.getState();
        int numRows = state.length;
        int numCols = state[0].length;


        for (int row = numRows; row > 0; row--) {
            Text text = new Text(Integer.toString(row - 1));
            text.setX(MARGIN_X - 20 + 350);
            text.setY(row * TILE_HEIGHT - 15);
            root.getChildren().add(text);
        }


        for (int col = numCols; col > 0; col--) {
            Text text = new Text(Integer.toString(col - 1));
            text.setX((col - 1) * TILE_WIDTH + MARGIN_X + 350 + 15);
            text.setY(numRows * TILE_HEIGHT + 20);
            root.getChildren().add(text);
        }


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                State indexState = state[row][col];
                ImageView imageView = new ImageView();
                String imagePath = stateImageMap.get(indexState);
                imageView.setImage(new Image(imagePath, 40, 40, false, false));
                imageView.setX(MARGIN_X + col * TILE_WIDTH + 350);
                imageView.setY(row * TILE_HEIGHT);
                root.getChildren().add(imageView);
            }
        }


        //  redraw hand cards, the hand string in gameState has been updated
        final int cardSpacingVertical = TILE_HEIGHT / 3;
        final int cardsPerColumn = 3;
        final int columnWidth = TILE_WIDTH * 4;
        Hand hands = new Hand(gameState[2]);
        PathwayCard[] cards = hands.getCards_in_hand();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) continue;
            int n = switch (i) {
                case 0 -> 0;
                case 1 -> 3;
                case 2 -> 1;
                case 3 -> 4;
                case 4 -> 2;
                case 5 -> 5;
                default -> -1;
            };
            int column = n / cardsPerColumn;
            int offY = (n % cardsPerColumn) * (TILE_HEIGHT * 3 + cardSpacingVertical) + 120;
            int offX = MARGIN_X + (column % 2) * columnWidth;
            State[] states = cards[i].getState();

            // Create a container for all nine images of the card
            Group cardGroup = new Group();
            cardGroup.prefWidth(TILE_WIDTH * 3);
            cardGroup.prefHeight(TILE_HEIGHT * 3);

            // Set ID for each card
            String cardId = "" + cards[i].getDeck_ID() + cards[i].getID() + "-" + cards[i].getOrientation().toChar();
            cardGroup.setId(cardId);

            for (int j = 0; j < 9; j++) {
                ImageView imageViewHand = new ImageView();
                String imagePath = stateImageMap.get(states[j]);

                imageViewHand.setImage(new Image(imagePath, TILE_WIDTH, TILE_HEIGHT, false, false));

                // Set the position of each image view within the card group
                switch (j) {
                    case 0 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY);}
                    case 1 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY);}
                    case 2 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY);}
                    case 3 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 4 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 5 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + TILE_HEIGHT);}
                    case 6 -> {imageViewHand.setX(MARGIN_X + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 7 -> {imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                    case 8 -> {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX);imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                }

                // Add the image view to the card group
                cardGroup.getChildren().add(imageViewHand);
            }

            // Add text displaying the card ID below the card
            Text idText = new Text(cardId);
            idText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            idText.setFill(Color.BLACK);
            idText.setX(MARGIN_X + offX + TILE_WIDTH + 10);
            idText.setY(offY + TILE_HEIGHT * 3 + 10);
            // Add the card group and the ID text to the root
            root.getChildren().addAll(cardGroup, idText);
        }

        if(gameState[2].equals("ABCD")){
            gameState[3] = "";
            cardsDrawn = 0;
            cardsDrawnLabel.setText("Already drew: " + cardsDrawn);
        }


        ImageView imageView = new ImageView();
        Image image = new Image("comp1110/ass2/gui/assets/GameImage.png", 200, 200, false, false);
        imageView.setImage(image);
        imageView.setX(900);
        imageView.setY(650);

        root.getChildren().add(imageView);

        if(over){
            Alert gameOverAlert = new Alert(Alert.AlertType.WARNING);
            gameOverAlert.setTitle("You have won the game!");
            gameOverAlert.setContentText("""
                   All cats have safely reached the raft.""");
            gameOverAlert.showAndWait();
            return;
        }

        addShadow(gameState);

        //  music
        Media catsound = new Media(getClass().getResource("/comp1110/ass2/gui/assets/cat.mp3").toURI().toString());
        catPlayer = new MediaPlayer(catsound);
        catPlayer.setCycleCount(1);
        catPlayer.play();

    }


    //  Add shade to an exhausted cat.
    private void addShadow(String[] gameState) {

        root.getChildren().removeIf(node -> node instanceof Rectangle);

        String cats = gameState[3];
        if(cats.isEmpty()){
            return;
        }

        Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})");
        Matcher matcher = pattern.matcher(cats);

        ArrayList<int[]> coordinatesList = new ArrayList<>();

        while (matcher.find()) {
            String row = matcher.group(1);
            String col = matcher.group(2);
            int[] coordinate = {Integer.parseInt(row), Integer.parseInt(col)};
            coordinatesList.add(coordinate);
        }

        int[][] coordinatesArray = coordinatesList.toArray(new int[0][]);

        for(int[] cat: coordinatesArray) {
            int row = cat[0];
            int col = cat[1];

            DropShadow dropShadow = new DropShadow(10, Color.RED);
            Rectangle shadow = new Rectangle(40, 40);
            shadow.setFill(Color.TRANSPARENT);
            shadow.setStroke(Color.RED);
            shadow.setStrokeWidth(3);
            shadow.setEffect(dropShadow);
            shadow.setX(MARGIN_X + col * TILE_WIDTH + 350);
            shadow.setY(row * TILE_HEIGHT);

            root.getChildren().add(shadow);
        }
    }

}


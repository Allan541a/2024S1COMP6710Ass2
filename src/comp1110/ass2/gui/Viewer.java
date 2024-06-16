package comp1110.ass2.gui;

import comp1110.ass2.BoardState;
import comp1110.ass2.Hand;
import comp1110.ass2.PathwayCard;
import comp1110.ass2.State;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;


//  Authored by James Engeman U7484227.

public class Viewer extends Application {

    private final Group root = new Group();
    private static final int VIEWER_WIDTH = 1100;
    private static final int VIEWER_HEIGHT = 650;

    private static final int TILE_WIDTH = 40;
    private static final int TILE_HEIGHT = 40;
    private static final int MARGIN_X = 20;
    private final Group controls = new Group();
    private TextArea handTextField;
    private TextArea boardTextField;
    private TextArea challengeTextField;

    /**
     * Draw the given board and hand in the window, removing any previously drawn boards/hands.
     *
     * @param boardstate newline separated string representing each row of the board (the board string, see the STRING-REPRESENTATION.md for more details
     * @param hand A string representing the cards in a player's hand (the hand string, see the STRING-REPRESENTATION.md for more details)
     *
     */

    void displayState(String boardstate, String hand) {

        HashMap<State, String> stateImageMap = new HashMap<>();
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
        // FIXME TASK 4
        root.getChildren().removeIf(node -> node instanceof ImageView);

        BoardState s = new BoardState(boardstate);
        State[][] state = s.getState();

        int numRows = state.length;
        int numCols = state[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                State indexState = state[row][col];
                ImageView imageView = new ImageView();
                String imagePath = stateImageMap.get(indexState);
                if (imagePath == null) {
                    System.err.println("Invalid character found in boardstate string: '" + indexState.toChar() + "'");
                    continue;
                }
                imageView.setImage(new Image(imagePath, TILE_WIDTH, TILE_HEIGHT, false, false));
                imageView.setX(MARGIN_X + col * TILE_WIDTH + 350);
                imageView.setY(row * TILE_HEIGHT);
                root.getChildren().add(imageView);
            }
        }


        final int cardSpacingVertical = TILE_HEIGHT/3;
        final int cardsPerColumn = 3;
        final int columnWidth = TILE_WIDTH * 4;
        Hand hands = new Hand(hand);
        PathwayCard[] cards = hands.getCards_in_hand();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) continue;
            int n = switch (i){
                case 0 -> 0;
                case 1 -> 3;
                case 2 -> 1;
                case 3 -> 4;
                case 4 -> 2;
                case 5 -> 5;
                default -> -1;
            };
            int column = n / cardsPerColumn;
            int offY = (n % cardsPerColumn) * (TILE_HEIGHT * 3 + cardSpacingVertical);
            int offX = MARGIN_X + (column % 2) * columnWidth;
            State[] states = cards[i].getState();
            for (int j = 0; j < 9; j++) {
                ImageView imageViewHand = new ImageView();
                String imagePath = stateImageMap.get(states[j]);

                if (imagePath == null) {
                    System.err.println("Invalid character found in boardstate string: '" + states[j].toChar() + "'");
                    continue;
                }
                imageViewHand.setImage(new Image(imagePath, TILE_WIDTH, TILE_HEIGHT, false, false));
                if (j == 0){imageViewHand.setX(MARGIN_X + offX); imageViewHand.setY(offY);}
                else if (j == 1){imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX); imageViewHand.setY(offY);}
                else if (j == 2){imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX); imageViewHand.setY(offY);}
                else if (j == 3){imageViewHand.setX(MARGIN_X + offX); imageViewHand.setY(offY + TILE_HEIGHT);}
                else if (j == 4){imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX); imageViewHand.setY(offY + TILE_HEIGHT);}
                else if (j == 5){imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX); imageViewHand.setY(offY + TILE_HEIGHT);}
                else if (j == 6){imageViewHand.setX(MARGIN_X + offX); imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                else if (j == 7){imageViewHand.setX(MARGIN_X + TILE_WIDTH + offX); imageViewHand.setY(offY + (TILE_HEIGHT * 2));}
                else {imageViewHand.setX(MARGIN_X + (TILE_WIDTH * 2) + offX); imageViewHand.setY(offY + (TILE_HEIGHT * 2));}

                root.getChildren().add(imageViewHand);
            }
        }
    }



    /**
     * Generate controls for Viewer
     */

    private void makeControls() {
        Label playerLabel = new Label("Cards in hand:");
        handTextField = new TextArea();
        handTextField.setPrefWidth(100);
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextArea();
        boardTextField.setPrefWidth(100);
        Label challengeTextLabel = new Label("Challenge Level: \n (between 1 and 5)");
        challengeTextField = new TextArea();
        challengeTextField.setPrefWidth(100);
        HBox textBox = new HBox();
        textBox.getChildren().addAll(handTextField, boardTextField, challengeTextField);
        textBox.setSpacing(20);
        textBox.setLayoutX(MARGIN_X);
        textBox.setLayoutY(VIEWER_HEIGHT - 220);

        Button refreshButton = refreshButton();
        refreshButton.setLayoutY(VIEWER_HEIGHT - 250);
        refreshButton.setLayoutX(MARGIN_X);

        Button rotateButton = rotateButton();
        rotateButton.setLayoutY(VIEWER_HEIGHT - 250);
        rotateButton.setLayoutX(MARGIN_X * 7);


        HBox fields = new HBox();
        fields.getChildren().addAll(handTextField, boardTextField,challengeTextField);
        fields.setSpacing(20);
        fields.setLayoutX(MARGIN_X);
        fields.setLayoutY(VIEWER_HEIGHT - 185);
        HBox labels = new HBox();
        labels.getChildren().addAll(playerLabel, boardLabel, challengeTextLabel);
        labels.setSpacing(45);
        labels.setLayoutX(MARGIN_X);
        labels.setLayoutY(VIEWER_HEIGHT - 220);
        controls.getChildren().addAll(fields, labels, refreshButton, rotateButton, textBox);
    }

    /**
     * Create refresh button. Upon pressing, capture the textFields and call displayState
     * @return the created button
     */
    private Button refreshButton() {
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            String boardText = boardTextField.getText();
            String handCards = handTextField.getText();
            displayState(boardText, handCards);
        });
        return refreshButton;
    }

    private Button rotateButton(){
        Button rotateButton = new Button("Rotate");
        rotateButton.setOnAction(r -> {
            //rotate whichever card is selected
        });
        return rotateButton;
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Race to the Raft Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeControls();
        displayState(
                "fffffffffffffffrrf\nfffffffffbBbfffrRf\nfffffffffbbbfffrrf\nffffffpbgggbyrybgp\nffffffpryyyrgbrbgy\nffffffygbggrbpygpp\nffffppbpgypbprgyry\nffffPprybbrgypbbgb\nffffpprgygbybgryyb\nffffffgpbbrpygybgy\nffffffbyrgrbgyrpbg\nffffffpgygyppbyyrp\nffffffbgbybygbrprp\nfffbBbybpgyrpgbbow\nfffbbbyrypbbgrpbyg\n", "AfhkBCDahw");
        root.getChildren().add(controls);
        makeControls();
        stage.setScene(scene);
        stage.show();
    }
}


/*public class GamewithDrag extends Application {
    private final Group root = new Group();  // Root group to hold all UI components
    private static final int VIEWER_WIDTH = 1100;  // Viewer window width
    private static final int VIEWER_HEIGHT = 650;  // Viewer window height
    private static final int TILE_WIDTH = 40;  // Width of each tile
    private static final int TILE_HEIGHT = 40;  // Height of each tile
    private static final int MARGIN_X = 20;  // Horizontal margin
    private static final int MARGIN_Y = 20;  // Vertical margin
    private final int startX = MARGIN_X + 350;  // Start X position for the board
    private final int startY = MARGIN_Y;  // Start Y position for the board
    private final Group controls = new Group();  // Group to hold control elements

    RaceToTheRaft game;  // Instance of the game

    // Method to display the current state of the board and hand
    void displayState(String boardstate, String hand) {
        root.getChildren().clear();  // Clear existing tiles and labels

        String imagePath = "comp1110/ass2/gui/assets/";
        BoardState s = new BoardState(boardstate);
        State[][] state = s.getState();

        int numRows = state.length;
        int numCols = state[0].length;

        // Add row and column labels
        VBox rowLabels = createRowLabels();
        HBox columnLabels = createColumnLabels(numRows);

        root.getChildren().addAll(rowLabels, columnLabels);

        // Iterate through each tile in the board state
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                State indexState = state[row][col];
                ImageView imageView = new ImageView();
                setImage(indexState, imageView, imagePath);  // Set image for the tile

                imageView.setX(startX + col * TILE_WIDTH);  // Set X position
                imageView.setY(startY + row * TILE_HEIGHT);  // Set Y position
                enableDrag(imageView, row, col, state);  // Enable drag functionality

                root.getChildren().add(imageView);  // Add tile to the root group
            }
        }
    }

    // Method to set the image for a given tile based on its state
    void setImage(State indexState, ImageView imageView, String imagePath) {
        switch (indexState) {
            case BLUE -> imageView.setImage(new Image(imagePath + "blue.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case BLUE_CAT -> imageView.setImage(new Image(imagePath + "blueCat.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case FIRE -> imageView.setImage(new Image(imagePath + "fire.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case GREEN -> imageView.setImage(new Image(imagePath + "green.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case GREEN_CAT -> imageView.setImage(new Image(imagePath + "greenCat.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case PURPLE -> imageView.setImage(new Image(imagePath + "purple.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case PURPLE_CAT -> imageView.setImage(new Image(imagePath + "purpleCat.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case RED -> imageView.setImage(new Image(imagePath + "red.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case RED_CAT -> imageView.setImage(new Image(imagePath + "redCat.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case YELLOW -> imageView.setImage(new Image(imagePath + "yellow.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case YELLOW_CAT -> imageView.setImage(new Image(imagePath + "yellowCat.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            case OBJECTIVE, WILD -> imageView.setImage(new Image(imagePath + "objective.png", TILE_WIDTH, TILE_HEIGHT, false, false));
            default -> System.err.println("Invalid state found in boardstate string: '" + indexState + "'");
        }
    }

    // Method to check if a tile is a "cat tile"
    private boolean isCatTile(State state) {
        return state == State.BLUE_CAT || state == State.GREEN_CAT ||
                state == State.PURPLE_CAT || state == State.RED_CAT ||
                state == State.YELLOW_CAT;
    }

    // Method to enable dragging functionality for a tile
    private void enableDrag(ImageView imageView, int row, int col, State[][] state) {
        if (!isCatTile(state[row][col])) {
            return;  // If not a "cat tile", exit without adding drag functionality
        }
        final double[] lastX = new double[1];
        final double[] lastY = new double[1];

        imageView.setOnMousePressed(event -> {
            // Focus the ImageView to receive key events
            imageView.requestFocus();
            lastX[0] = event.getSceneX();
            lastY[0] = event.getSceneY();
            imageView.toFront();
        });

        imageView.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - lastX[0];
            double deltaY = event.getSceneY() - lastY[0];
            imageView.setX(imageView.getX() + deltaX);
            imageView.setY(imageView.getY() + deltaY);
            lastX[0] = event.getSceneX();
            lastY[0] = event.getSceneY();
        });

        imageView.setOnMouseReleased(event -> {
            // Snap to grid or adjust based on game rules
            snapToGrid(imageView, state, row, col);
        });

        imageView.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
                rotateTile(imageView, 90); // Rotate clockwise
            }
        });
    }

    // Method to snap a tile back to the grid
    private void snapToGrid(ImageView imageView, State[][] state, int row, int col) {
        int newCol = (int) Math.max(0, Math.min(state[0].length - 1, Math.round((imageView.getX() - startX) / TILE_WIDTH)));
        int newRow = (int) Math.max(0, Math.min(state.length - 1, Math.round((imageView.getY() - startY) / TILE_HEIGHT)));

        imageView.setX(startX + newCol * TILE_WIDTH);
        imageView.setY(startY + newRow * TILE_HEIGHT);
        updateGameState(state, row, col, newRow, newCol);
    }

    // Method to rotate a tile
    private void rotateTile(ImageView imageView, int angle) {
        imageView.setRotate(imageView.getRotate() + angle);
    }

    // Method to update the game state when a tile is moved
    private void updateGameState(State[][] state, int oldRow, int oldCol, int newRow, int newCol) {
        State temp = state[oldRow][oldCol];
        state[oldRow][oldCol] = state[newRow][newCol];
        state[newRow][newCol] = temp;
    }

    // Method to create row labels from 1 to 18
    private VBox createRowLabels() {
        VBox vBox = new VBox();
        for (int i = 1; i <= 15; i++) {  // Always print 1 through 18 for rows
            Label label = new Label(String.valueOf(i));
            label.setMinSize(TILE_WIDTH, TILE_HEIGHT);
            vBox.getChildren().add(label);
        }
        vBox.setLayoutX(startX - TILE_WIDTH);  // Positioning left of the board with a slight margin
        vBox.setLayoutY(startY);
        return vBox;
    }

    // Method to create column labels from 1 to 15
    private HBox createColumnLabels(int numRows) {
        HBox hBox = new HBox();
        for (int i = 1; i <= 18; i++) {  // Always print 1 through 15 for columns
            Label label = new Label(String.valueOf(i));
            label.setMinSize(TILE_WIDTH, TILE_HEIGHT);
            hBox.getChildren().add(label);
        }
        hBox.setLayoutX(startX);  // Positioning aligned with the start of the tiles
        hBox.setLayoutY(startY + numRows * TILE_HEIGHT);  // Positioning below the board with a slight margin
        return hBox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, VIEWER_WIDTH, VIEWER_HEIGHT);
        stage.setTitle("Race to the Raft Viewer");
        displayState(
                "fffffffffffffffrrf\nfffffffffbBbfffrRf\nfffffffffbbbfffrrf\nffffffpbgggbyrybgp\nffffffpryyyrgbrbgy\nffffffygbggrbpygpp\nffffppbpgypbprgyry\nffffPprybbrgypbbgb\nffffpprgygbybgryyb\nffffffgpbbrpygybgy\nffffffbyrgrbgyrpbg\nffffffpgygyppbyyrp\nffffffbgbybygbrprp\nfffbBbybpgyrpgbbow\nfffbbbyrypbbgrpbyg\n", "AfhkBCDahw");
        root.getChildren().add(controls);
        stage.setScene(scene);
        stage.show();
    }
}*/
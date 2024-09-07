package ttn.developer.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class TicTacToeController implements Initializable {
    char turn = 'X';
    char gameState = 'N'; // N for new game and selection, R for running and F for Finished.
    char difficulty = 'F'; // F for easy, M for medium y D for Hard.

    Image circleImage = new Image(String.valueOf(TicTacToeApplication.class.getResource("icons/circle.png")));
    Image crossImage = new Image(String.valueOf(TicTacToeApplication.class.getResource("icons/cross.png")));
    Image drawImage = new Image(String.valueOf(TicTacToeApplication.class.getResource("icons/draw.png")));
    Image exitImage = new Image(String.valueOf(TicTacToeApplication.class.getResource("icons/exit.png")));

    Stage stage;
    @FXML
    BorderPane mainPane;
    @FXML
    Label difficultyLabel;
    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;
    @FXML
    Button button4;
    @FXML
    Button button5;
    @FXML
    Button button6;
    @FXML
    Button button7;
    @FXML
    Button button8;
    @FXML
    Button button9;
    @FXML
    ImageView currentTurn;
    @FXML
    Label turnText;
    @FXML
    ImageView crossCounterImage;
    @FXML
    Label crossCounter;
    @FXML
    ImageView circleCounterImage;
    @FXML
    Label circleCounter;
    @FXML
    ImageView drawCounterImage;
    @FXML
    Label drawCounter;
    int oMoves = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crossCounterImage.setImage(crossImage);
        crossCounter.setText("0");
        circleCounterImage.setImage(circleImage);
        circleCounter.setText("0");
        drawCounterImage.setImage(drawImage);
        drawCounter.setText("0");
        currentTurn.setImage(turn == 'X' ? crossImage : circleImage);
        setDifficulty('F');
    }

    private void setDifficulty(char difficulty) {
        switch (difficulty) {
            case 'F':
                difficultyLabel.setText("Dificultad: Fácil");
                this.difficulty = 'F';
                break;
            case 'M':
                difficultyLabel.setText("Dificultad: Medio");
                this.difficulty = 'M';
                break;
            case 'D':
                difficultyLabel.setText("Dificultad: Difícil");
                this.difficulty = 'D';
            break;
        }
    }

    @FXML
    private void setEasy() {
        setDifficulty('F');
    }

    @FXML
    private void setMedium() {
        setDifficulty('M');
    }

    @FXML
    private void setHard() {
        setDifficulty('D');
    }

    @FXML
    private void onActionButton(ActionEvent event) {
        if (gameState == 'F') return;
        gameState = 'R';
        Button button = (Button) event.getSource();
        if (button.getGraphic() != null) return;
        setButtonGraphic(button);
        toggleTurn();
    }

    private void toggleTurn() {
        defineWinner();
        if (turn == 'X' && gameState == 'R') {
            turn = 'O';
            currentTurn.setImage(circleImage);
            circlePcTurn();
        } else if (turn == 'O' && gameState == 'R'){
            turn = 'X';
            currentTurn.setImage(crossImage);
        }
    }

    private void circlePcTurn() {
        oMoves++;
        switch (difficulty) {
            case 'F':
                pressRandomButton();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case 'M':
                if (oMoves == 1 || oMoves == 2) {
                    think();
                } else {
                    pressRandomButton();
                }
                break;
            case 'D':
                think();
                break;
            default:
        }
    }

    private void setButtonGraphic(Button button) {
        ImageView imageView = new ImageView(turn == 'X' ? crossImage : circleImage);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setId(turn + button.getId());

        button.setGraphic(imageView);
    }

    private void defineWinner() {
        //Get IDs buttons graphics
        String idBtnGraphic1 = button1.getGraphic() != null ? button1.getGraphic().getId() : "NA";
        String idBtnGraphic2 = button2.getGraphic() != null ? button2.getGraphic().getId() : "NA";
        String idBtnGraphic3 = button3.getGraphic() != null ? button3.getGraphic().getId() : "NA";
        String idBtnGraphic4 = button4.getGraphic() != null ? button4.getGraphic().getId() : "NA";
        String idBtnGraphic5 = button5.getGraphic() != null ? button5.getGraphic().getId() : "NA";
        String idBtnGraphic6 = button6.getGraphic() != null ? button6.getGraphic().getId() : "NA";
        String idBtnGraphic7 = button7.getGraphic() != null ? button7.getGraphic().getId() : "NA";
        String idBtnGraphic8 = button8.getGraphic() != null ? button8.getGraphic().getId() : "NA";
        String idBtnGraphic9 = button9.getGraphic() != null ? button9.getGraphic().getId() : "NA";

        //Cases when X win.
        //Horizontal
        if (idBtnGraphic1.startsWith("X") && idBtnGraphic2.startsWith("X") && idBtnGraphic3.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic4.startsWith("X") && idBtnGraphic5.startsWith("X") && idBtnGraphic6.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic7.startsWith("X") && idBtnGraphic8.startsWith("X") && idBtnGraphic9.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        //Vertical
        if (idBtnGraphic1.startsWith("X") && idBtnGraphic4.startsWith("X") && idBtnGraphic7.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic2.startsWith("X") && idBtnGraphic5.startsWith("X") && idBtnGraphic8.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic3.startsWith("X") && idBtnGraphic6.startsWith("X") && idBtnGraphic9.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        //Diagonal
        if (idBtnGraphic1.startsWith("X") && idBtnGraphic5.startsWith("X") && idBtnGraphic9.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic3.startsWith("X") && idBtnGraphic5.startsWith("X") && idBtnGraphic7.startsWith("X")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(crossImage);
            increaseCrossCounter();
            gameState = 'F';
            return;
        }

        //Cases when O win.
        //Horizontal
        if (idBtnGraphic1.startsWith("O") && idBtnGraphic2.startsWith("O") && idBtnGraphic3.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic4.startsWith("O") && idBtnGraphic5.startsWith("O") && idBtnGraphic6.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic7.startsWith("O") && idBtnGraphic8.startsWith("O") && idBtnGraphic9.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        //Vertical
        if (idBtnGraphic1.startsWith("O") && idBtnGraphic4.startsWith("O") && idBtnGraphic7.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic2.startsWith("O") && idBtnGraphic5.startsWith("O") && idBtnGraphic8.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic3.startsWith("O") && idBtnGraphic6.startsWith("O") && idBtnGraphic9.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        //Diagonal
        if (idBtnGraphic1.startsWith("O") && idBtnGraphic5.startsWith("O") && idBtnGraphic9.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        if (idBtnGraphic3.startsWith("O") && idBtnGraphic5.startsWith("O") && idBtnGraphic7.startsWith("O")) {
            turnText.setText("Ganador:");
            currentTurn.setImage(circleImage);
            increaseCircleCounter();
            gameState = 'F';
            return;
        }
        //Draw case
        if (gameState == 'R' && !idBtnGraphic1.equals("NA") && !idBtnGraphic2.equals("NA") && !idBtnGraphic3.equals("NA")
                && !idBtnGraphic4.equals("NA") && !idBtnGraphic5.equals("NA") && !idBtnGraphic6.equals("NA")
                && !idBtnGraphic7.equals("NA") && !idBtnGraphic8.equals("NA") && !idBtnGraphic9.equals("NA")) {
            turnText.setText("Empate");
            increaseDrawCounter();
            currentTurn.setImage(drawImage);
            gameState = 'F';
        }
    }

    private void increaseDrawCounter() {
        drawCounter.setText((Integer.parseInt(drawCounter.getText()) + 1) + "");
    }
    private void increaseCrossCounter() {
        crossCounter.setText((Integer.parseInt(crossCounter.getText()) + 1) + "");
    }
    private void increaseCircleCounter() {
        circleCounter.setText((Integer.parseInt(circleCounter.getText()) + 1) + "");
    }

    @FXML
    private void newGame() {
        gameState = 'N';
        button1.setGraphic(null);
        button2.setGraphic(null);
        button3.setGraphic(null);
        button4.setGraphic(null);
        button5.setGraphic(null);
        button6.setGraphic(null);
        button7.setGraphic(null);
        button8.setGraphic(null);
        button9.setGraphic(null);
        turn = 'X';
        currentTurn.setImage(crossImage);
        turnText.setText("Turno de:");
        oMoves = 0;
    }

    @FXML
    private void resetCounters() {
        drawCounter.setText("0");
        crossCounter.setText("0");
        circleCounter.setText("0");
    }

    @FXML
    private void closeApplication() {
        ImageView imageView = new ImageView(exitImage);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar juego");
        alert.setHeaderText("Estás a punto de salir del juego.");
        alert.setContentText("¿Estás seguro?");
        alert.setGraphic(imageView);

        if (alert.showAndWait().isPresent() && alert.getResult() == ButtonType.OK) {
            stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        }
    }

    private void pressRandomButton() {
        //Get IDs buttons graphics
        String idBtnGraphic1 = button1.getGraphic() != null ? button1.getGraphic().getId() : "NA";
        String idBtnGraphic2 = button2.getGraphic() != null ? button2.getGraphic().getId() : "NA";
        String idBtnGraphic3 = button3.getGraphic() != null ? button3.getGraphic().getId() : "NA";
        String idBtnGraphic4 = button4.getGraphic() != null ? button4.getGraphic().getId() : "NA";
        String idBtnGraphic5 = button5.getGraphic() != null ? button5.getGraphic().getId() : "NA";
        String idBtnGraphic6 = button6.getGraphic() != null ? button6.getGraphic().getId() : "NA";
        String idBtnGraphic7 = button7.getGraphic() != null ? button7.getGraphic().getId() : "NA";
        String idBtnGraphic8 = button8.getGraphic() != null ? button8.getGraphic().getId() : "NA";
        String idBtnGraphic9 = button9.getGraphic() != null ? button9.getGraphic().getId() : "NA";

        LinkedList<Integer> freeButtons = new LinkedList<>();
        if (Objects.equals(idBtnGraphic1, "NA")) freeButtons.add(1);
        if (Objects.equals(idBtnGraphic2, "NA")) freeButtons.add(2);
        if (Objects.equals(idBtnGraphic3, "NA")) freeButtons.add(3);
        if (Objects.equals(idBtnGraphic4, "NA")) freeButtons.add(4);
        if (Objects.equals(idBtnGraphic5, "NA")) freeButtons.add(5);
        if (Objects.equals(idBtnGraphic6, "NA")) freeButtons.add(6);
        if (Objects.equals(idBtnGraphic7, "NA")) freeButtons.add(7);
        if (Objects.equals(idBtnGraphic8, "NA")) freeButtons.add(8);
        if (Objects.equals(idBtnGraphic9, "NA")) freeButtons.add(9);

        Random random = new Random();
        int selectedIndex = random.nextInt(freeButtons.size()) + 1;
        int selectedButton = freeButtons.get(selectedIndex - 1);

        switch (selectedButton) {
            case 1: button1.fire(); break;
            case 2: button2.fire(); break;
            case 3: button3.fire(); break;
            case 4: button4.fire(); break;
            case 5: button5.fire(); break;
            case 6: button6.fire(); break;
            case 7: button7.fire(); break;
            case 8: button8.fire(); break;
            case 9: button9.fire(); break;
        }
    }

    private void think() {
        String idBtnGraphic1 = button1.getGraphic() != null ? button1.getGraphic().getId() : "NA";
        String idBtnGraphic2 = button2.getGraphic() != null ? button2.getGraphic().getId() : "NA";
        String idBtnGraphic3 = button3.getGraphic() != null ? button3.getGraphic().getId() : "NA";
        String idBtnGraphic4 = button4.getGraphic() != null ? button4.getGraphic().getId() : "NA";
        String idBtnGraphic5 = button5.getGraphic() != null ? button5.getGraphic().getId() : "NA";
        String idBtnGraphic6 = button6.getGraphic() != null ? button6.getGraphic().getId() : "NA";
        String idBtnGraphic7 = button7.getGraphic() != null ? button7.getGraphic().getId() : "NA";
        String idBtnGraphic8 = button8.getGraphic() != null ? button8.getGraphic().getId() : "NA";
        String idBtnGraphic9 = button9.getGraphic() != null ? button9.getGraphic().getId() : "NA";

        int [] board = {
                idBtnGraphic1.startsWith("X") ? 1 : idBtnGraphic1.startsWith("O") ? -1 : 0,
                idBtnGraphic2.startsWith("X") ? 1 : idBtnGraphic2.startsWith("O") ? -1 : 0,
                idBtnGraphic3.startsWith("X") ? 1 : idBtnGraphic3.startsWith("O") ? -1 : 0,
                idBtnGraphic4.startsWith("X") ? 1 : idBtnGraphic4.startsWith("O") ? -1 : 0,
                idBtnGraphic5.startsWith("X") ? 1 : idBtnGraphic5.startsWith("O") ? -1 : 0,
                idBtnGraphic6.startsWith("X") ? 1 : idBtnGraphic6.startsWith("O") ? -1 : 0,
                idBtnGraphic7.startsWith("X") ? 1 : idBtnGraphic7.startsWith("O") ? -1 : 0,
                idBtnGraphic8.startsWith("X") ? 1 : idBtnGraphic8.startsWith("O") ? -1 : 0,
                idBtnGraphic9.startsWith("X") ? 1 : idBtnGraphic9.startsWith("O") ? -1 : 0,
        };

        IA ia = new IA();
        int best = ia.findBestMove(board) + 1;

        switch (best) {
            case 1: button1.fire(); break;
            case 2: button2.fire(); break;
            case 3: button3.fire(); break;
            case 4: button4.fire(); break;
            case 5: button5.fire(); break;
            case 6: button6.fire(); break;
            case 7: button7.fire(); break;
            case 8: button8.fire(); break;
            case 9: button9.fire(); break;
        }
    }
}
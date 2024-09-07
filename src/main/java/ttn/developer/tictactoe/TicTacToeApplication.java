package ttn.developer.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(String.valueOf(TicTacToeApplication.class.getResource("icons/favicon.png")));
        Image alertIcon = new Image(String.valueOf(TicTacToeApplication.class.getResource("icons/exit.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApplication.class.getResource("tit-tac-toe-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tic Tac Toe!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeApplication(stage, alertIcon);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void closeApplication(Stage stage, Image icon) {
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar juego");
        alert.setHeaderText("Estás a punto de salir del juego.");
        alert.setContentText("¿Estás seguro?");
        alert.setGraphic(imageView);

        if (alert.showAndWait().isPresent() && alert.getResult() == ButtonType.OK) {
            stage.close();
        }
    }
}
module ttn.developer.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens ttn.developer.tictactoe to javafx.fxml;
    exports ttn.developer.tictactoe;
}
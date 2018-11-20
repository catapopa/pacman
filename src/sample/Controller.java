package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Game game;
    @FXML
    GridPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            game = new Game();
            game.fileToMemory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Game drawTable(){

        //Cell[][] file =
    }

}

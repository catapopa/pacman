package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    Repository repository;

    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.repository = new Repository();
        try {
            this.fileToMemory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Cell[][] fileToMemory() throws FileNotFoundException {
        return repository.fileToMemory(this.gridPane);
    }

    public void drawTable() throws FileNotFoundException {
       Cell[][] file = this.fileToMemory();
        //Arrays.stream(file).map()
       // iterez matricea, create block, add to table, root.add(block, row, col)i, j
    }

}

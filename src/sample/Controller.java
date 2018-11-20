package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller {

    Repository repository;

    @FXML
    GridPane gridPane;


    public Controller(Repository repository) throws FileNotFoundException {
        this.repository = repository;
        this.fileToMemory();
    }

    public Cell[][] fileToMemory() throws FileNotFoundException {
        return repository.fileToMemory(this.gridPane);
    }

   /* public Repository drawTable() {
        //Cell[][] file = // iterez matricea, create block, add to table, root.add(block, row, col)i, j
    }*/

}

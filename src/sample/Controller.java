package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private Repository repository;
    @FXML
    GridPane root;

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
        return repository.fileToMemory(this.root);
    }

    public void drawTable() throws FileNotFoundException {
        //Cell[][] file = this.fileToMemory();
        //Arrays.stream(file).map()
        // iterez matricea, create block, add to table, root.add(block, row, col)i, j
    }

}

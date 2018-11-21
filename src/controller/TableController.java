package controller;

import domain.Cell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import repository.TableRepository;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class TableController implements Initializable {

    private TableRepository tableRepository;
    @FXML
    GridPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tableRepository = new TableRepository();
        try {
            this.fileToMemory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Cell[][] fileToMemory() throws FileNotFoundException {
        return tableRepository.fileToMemory(this.root);
    }

    public void drawTable() throws FileNotFoundException {
        //Cell[][] file = this.fileToMemory();
        //Arrays.stream(file).map()
        // iterez matricea, create block, add to table, root.add(block, row, col)i, j
    }

}

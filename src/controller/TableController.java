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
            this.tableRepository.fileToMemory();
            this.tableRepository.draw(this.root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

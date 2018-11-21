package sample;

import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;


/*Node: The abstract base class for all scene graph nodes.
Parent: The abstract base class for all branch nodes. (This class directly extends Node).
Scene: The base container class for all content in the scene graph.*/


public class Repository {

    private Cell[][] table;

    public Repository() {
        this.table = new Cell[10][10];
    }

    // file -> memory
    public Cell[][] fileToMemory(GridPane root) throws FileNotFoundException {

        String filePath = "/home/cata/projects/java/pacman/src/sample/board.txt";

        Scanner input = new Scanner(new File(filePath));
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> {
                            table[i][j] = new Cell(i, j, input.nextInt());
                            table[i][j].setNode();
                            root.getChildren().add(table[i][j].getNode());
                        }));
        return this.table;
    }



}

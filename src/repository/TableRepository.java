package repository;

import domain.Cell;
import domain.Wall;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;


/*Node: The abstract base class for all scene graph nodes.
Parent: The abstract base class for all branch nodes. (This class directly extends Node).
Scene: The base container class for all content in the scene graph.*/


public class TableRepository {

    private Cell[][] table;

    public TableRepository() {
        this.table = new Cell[10][10];
    }

    // file -> memory
    public void fileToMemory() throws FileNotFoundException {
        String filePath = "/home/cata/projects/java/pacman/src/assets/table.txt";
        Scanner input = new Scanner(new File(filePath));
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> {
                            boolean block = true;
                            if (input.nextInt() == 0) {
                                block = false;
                            }
                            table[i][j] = new Wall(i, j, block);
                            table[i][j].setNode();
                        }));
    }

    public void draw(GridPane root) {
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> {
                            root.add(this.table[i][j].getNode(), j, i);
                        }));
    }
}

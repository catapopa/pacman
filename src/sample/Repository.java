package sample;

import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Repository {

    private Cell[][] table = new Cell[10][10];

    public Repository(){}

    //    file -> memory
    public Cell[][] fileToMemory(GridPane gridPane) throws FileNotFoundException {

        String filePath = "/home/cata/projects/java/pacman/src/sample/board.txt";

        Scanner input = new Scanner(new File(filePath));
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> {
                            table[i][j] = new Cell(i, j, input.nextInt());
                            gridPane.getChildren().add(table[i][j]);
                        }));

        return this.table;
    }
}

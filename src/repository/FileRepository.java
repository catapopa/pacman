package repository;

import constants.Constants;
import domain.Wall;
import domain.Wall;

import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;


/*Node: The abstract base class for all scene graph nodes.
Parent: The abstract base class for all branch nodes. (This class directly extends Node).
Scene: The base container class for all content in the scene graph.*/


public class FileRepository {

    // file -> memory
    public Wall[][] fileToMemory() throws FileNotFoundException {
        Wall[][] wall = new Wall[10][10];
        String filePath = "/home/cata/projects/java/pacman/src/assets/table.txt";
        Scanner input = new Scanner(new File(filePath));
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> {
                            boolean block = true;
                            if (input.nextInt() == 0) {
                                block = false;
                            }
                            wall[i][j] = new Wall(i * Constants.cellSize, j * Constants.cellSize, block);
                        }));
        return wall;
    }
}

package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game {

    private Cell[][] table = new Cell[10][10];

    public Game() {
    }

    //    file -> memory
    public void fileToMemory() throws IOException {

        String filePath = "/home/cata/projects/java/pacman/src/sample/board.txt";
        Path path = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(path)) {

            //Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
            //Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" ")));

            Scanner input = new Scanner(new File(filePath));
            IntStream.range(0, 10)
                    .forEach(j -> IntStream.range(0, 10)
                            .forEach(i -> table[i][j] = new Cell(i, j, input.nextInt())));

            for (int a = 0; a <= 9; a++) {
                for (int b = 0; b <= 9; b++) {
                    System.out.println(table[a][b].getType());
                }
            }
            //words.forEach(System.out::println);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private List<String> cells = new ArrayList<>();
    private int[][] table = new int[10][10];

    public Game() {
    }

    //    file -> memory
    public void fileToMemory() throws IOException {

        String filePath = "/home/cata/projects/java/pacman/src/sample/board.txt";
        Path path = Paths.get(filePath);
        //int[][] table = new int[10][10];

       /* int[][] table1 = Files.lines(path)
                .map(item -> item.chars().filter(i -> (char) i != ' ')
                        .map(Character::getNumericValue)
                        .toArray())
                .toArray(int[][]::new);*/

        /*int[][] table2 = Files.lines(path)
                .map(item -> Pattern.compile(" ")
                        .splitAsStream(item)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        Stream.of(table2)
                .flatMap(Stream::of)
                .forEach(System.out::println);
*/
        try (BufferedReader br = Files.newBufferedReader(path)) {

            //br returns as stream and convert it into a List
            cells = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        cells.forEach(System.out::println);
    }

}

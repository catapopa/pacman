package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private int[][] table = new int[10][10];

    public Game() {
    }

    //    file -> memory
    public void fileToMemory() throws IOException {

        String filePath = "/home/cata/projects/java/pacman/src/sample/board.txt";
        Path path = Paths.get(filePath);
        List<String> cells = new ArrayList<>();
        int i = 0;
        int j = 0;
        int[][] table = new int[10][10];

        try (BufferedReader br = Files.newBufferedReader(path)) {

            //br returns as stream and convert it into a List
            //cells = br.lines().collect(Collectors.toList());
            Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
            Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" ")));
            //table = words.forEach(new Cell(i, j, 0));

            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }

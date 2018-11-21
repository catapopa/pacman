package controller;

import constants.Constants;
import domain.Wall;
import repository.FileRepository;

import java.io.FileNotFoundException;

public class WallController {

    private Wall[][] wallList;

    public WallController(){
        FileRepository fileRepository = new FileRepository();
        try {
            this.wallList = fileRepository.fileToMemory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Wall[][] getWallList() {
        return this.wallList;
    }

    public boolean checkCollision(int x, int y) {
        return this.wallList[x/Constants.cellSize][y/Constants.cellSize].isBlock();
    }
}

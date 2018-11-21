package controller;

import constants.Constants;
import domain.Pacman;
import repository.PacmanRepository;

public class PacmanController {
    private PacmanRepository pacmanRepository;
    private WallController wallController;


    public PacmanController(WallController wallController) {
        pacmanRepository = new PacmanRepository();
        this.wallController = wallController;
    }

    public void moveUp() {
        if (checkCollision("UP")) {
            return;
        }
        this.pacmanRepository.moveUp();
    }

    public void moveDown() {
        if (checkCollision("DOWN")) {
            return;
        }
        this.pacmanRepository.moveDown();
    }

    public void moveLeft() {
        if (checkCollision("LEFT")) {
            return;
        }
        this.pacmanRepository.moveLeft();
    }

    public void moveRight() {
        if (checkCollision("RIGHT")) {
            return;
        }
        this.pacmanRepository.moveRight();
    }

    private boolean checkCollision(String direction){
        Pacman pacman = Pacman.getInstance();
        int x = pacman.getX();
        int y = pacman.getY();
        switch (direction){
            case "UP":
               return wallController.checkCollision(x, y - Constants.cellSize);
            case "DOWN":
                return wallController.checkCollision(x, y + Constants.cellSize);
            case "LEFT":
                return wallController.checkCollision(x - Constants.cellSize, y);
            case "RIGHT":
                return wallController.checkCollision(x + Constants.cellSize, y);
        }
        return false;
    }
}

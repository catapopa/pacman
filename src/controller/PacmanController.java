package controller;

import constants.Constants;
import constants.Constants.Direction;
import domain.Pacman;
import repository.PacmanRepository;

public class PacmanController {
    private PacmanRepository pacmanRepository;
    private WallController wallController;


    public PacmanController(WallController wallController) {
        pacmanRepository = new PacmanRepository();
        this.wallController = wallController;
    }

    public void move(Direction direction) {
        Pacman pacman = Pacman.getInstance();
        int x = pacman.getX();
        int y = pacman.getY();
        switch (direction) {
            case UP:
                if(!wallController.checkCollision(x, y - Constants.cellSize)){
                   this.pacmanRepository.moveUp();
                }
                break;
            case DOWN:
                if(!wallController.checkCollision(x, y + Constants.cellSize)){
                    this.pacmanRepository.moveDown();
                }
                break;
            case LEFT:
                if(!wallController.checkCollision(x - Constants.cellSize, y)){
                    this.pacmanRepository.moveLeft();
                }
                break;
            case RIGHT:
                if(!wallController.checkCollision(x + Constants.cellSize, y)){
                    pacmanRepository.moveRight();
                }
                break;
        }
        pacman.getNode().relocate(pacman.getX(), pacman.getY());
    }
}

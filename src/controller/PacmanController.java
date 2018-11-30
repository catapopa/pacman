package controller;

import constants.Constants;
import constants.Constants.Direction;
import domain.Ghost;
import domain.Pacman;
import repository.PacmanRepository;

import java.util.List;
import java.util.stream.IntStream;

public class PacmanController {
    private PacmanRepository pacmanRepository;
    private WallController wallController;
    private GhostController ghostController;

    public PacmanController(WallController wallController, GhostController ghostController) {
        pacmanRepository = new PacmanRepository();
        this.wallController = wallController;
        this.ghostController = ghostController;
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
        List<Ghost> ghosts = this.ghostController.getGhostList();
        IntStream.range(0, ghosts.size())
                .forEach(i -> {
                    if (ghostController.pacmanVsGhost(ghosts.get(i))) {
                        System.out.println("Game Over");
                    }
                });
    }

}

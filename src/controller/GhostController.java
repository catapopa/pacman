package controller;

import constants.Constants;
import domain.Ghost;
import repository.GhostRepository;
import repository.PacmanRepository;

import java.util.*;

public class GhostController {

    enum Direction {UP, DOWN, LEFT, RIGHT}

    private GhostRepository ghostRepository;
    private WallController wallController;



    public GhostController(GhostRepository ghostRepository, WallController wallController) {
        this.ghostRepository = ghostRepository;
        this.wallController = wallController;


    }

    public Direction getRandomDirection() {
        Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    private void move(Ghost ghost) {

        Direction randomDirection = this.getRandomDirection();
        int x = ghost.getX();
        int y = ghost.getY();

        switch (randomDirection) {
            case UP:
                if (!wallController.checkCollision(x, y - Constants.cellSize)) {
                    this.ghostRepository.moveUp(ghost);
                }
            case DOWN:
                if (!wallController.checkCollision(x, y + Constants.cellSize)) {
                    this.ghostRepository.moveUp(ghost);
                }
            case LEFT:
                if (!wallController.checkCollision(x - Constants.cellSize, y)) {
                    this.ghostRepository.moveUp(ghost);
                }
            case RIGHT:
                if (!wallController.checkCollision(x + Constants.cellSize, y) {
                this.ghostRepository.moveUp(ghost);
            }
        }
    }
}

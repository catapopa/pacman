package controller;

import constants.Constants;
import domain.Ghost;
import repository.GhostRepository;
import repository.PacmanRepository;

import java.util.*;
import java.util.stream.IntStream;

public class GhostController {

    enum Direction {UP, DOWN, LEFT, RIGHT}

    public enum SpeedOption {SLOW, MEDIUM, FAST}

    public SpeedOption selectedSpeed;
    private HashMap<SpeedOption, Integer> speed;
    private Timer timer;
    private GhostRepository ghostRepository;
    private WallController wallController;


    public GhostController(GhostRepository ghostRepository, WallController wallController, SpeedOption selectedSpeed) {
        this.speed = new HashMap<>();
        this.speed.put(SpeedOption.SLOW, 1000);
        this.speed.put(SpeedOption.MEDIUM, 500);
        this.speed.put(SpeedOption.FAST, 250);
        this.selectedSpeed = selectedSpeed;

        this.timer = new Timer();
        this.ghostRepository = ghostRepository;
        this.wallController = wallController;
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            move();
        }
    };

    public void start() {
        timer.scheduleAtFixedRate(timerTask, 1000, speed.get(this.selectedSpeed));
    }

    public Direction getRandomDirection() {
        Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    private void move() {
        List<Ghost> ghosts = this.ghostRepository.getGhostList();
        IntStream.range(0, ghosts.size())
                .forEach(i -> this.checkCollision(ghosts.get(i)));
    }

    private void checkCollision(Ghost ghost) {
        Direction randomDirection = this.getRandomDirection();
        int x = ghost.getX();
        int y = ghost.getY();

        switch (randomDirection) {
            case UP:
                if (!wallController.checkCollision(x, y - Constants.cellSize)) {
                    this.ghostRepository.moveUp(ghost);
                    ghost.getNode().relocate(x, y);
                }
            case DOWN:
                if (!wallController.checkCollision(x, y + Constants.cellSize)) {
                    this.ghostRepository.moveDown(ghost);
                    ghost.getNode().relocate(x, y);
                }
            case LEFT:
                if (!wallController.checkCollision(x - Constants.cellSize, y)) {
                    this.ghostRepository.moveLeft(ghost);
                    ghost.getNode().relocate(x, y);
                }
            case RIGHT:
                if (!wallController.checkCollision(x + Constants.cellSize, y)) {
                    this.ghostRepository.moveRight(ghost);
                    ghost.getNode().relocate(x, y);
                }
        }
    }
}

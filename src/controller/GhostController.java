package controller;

import constants.Constants;
import constants.Constants.SpeedOption;
import constants.Constants.Direction;
import domain.Ghost;
import repository.GhostRepository;

import java.util.*;
import java.util.stream.IntStream;

public class GhostController {

    private SpeedOption selectedSpeed;
    private HashMap<SpeedOption, Integer> speed;
    private Timer timer;
    private GhostRepository ghostRepository;
    private WallController wallController;

    public GhostController(WallController wallController, SpeedOption selectedSpeed, int numberOfGhosts) {
        this.speed = new HashMap<>();
        this.speed.put(SpeedOption.SLOW, 1000);
        this.speed.put(SpeedOption.MEDIUM, 500);
        this.speed.put(SpeedOption.FAST, 80);
        this.selectedSpeed = selectedSpeed;

        this.timer = new Timer();
        this.ghostRepository = new GhostRepository(numberOfGhosts);
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
        if (ghost.getDirection() == null) {
            ghost.setDirection(this.getRandomDirection());
        }

        int x = ghost.getX();
        int y = ghost.getY();

        switch (ghost.getDirection()) {
            case UP:
                if (wallController.checkCollision(x, y - Constants.cellSize)) {
                    ghost.setDirection(this.getRandomDirection());
                    break;
                }
                this.ghostRepository.moveUp(ghost);
                break;

            case DOWN:
                if (wallController.checkCollision(x, y + Constants.cellSize)) {
                    ghost.setDirection(this.getRandomDirection());
                    break;
                }
                this.ghostRepository.moveDown(ghost);
                break;
            case LEFT:
                if (wallController.checkCollision(x - Constants.cellSize, y)) {
                    ghost.setDirection(this.getRandomDirection());
                    break;
                }
                this.ghostRepository.moveLeft(ghost);
                break;

            case RIGHT:
                if (wallController.checkCollision(x + Constants.cellSize, y)) {
                    ghost.setDirection(this.getRandomDirection());
                    break;
                }
                this.ghostRepository.moveRight(ghost);
                break;

        }
        ghost.getNode().relocate(x, y);
    }

    public List<Ghost> getGhostList() {
        return this.ghostRepository.getGhostList();
    }
}

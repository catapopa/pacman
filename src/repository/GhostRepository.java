package repository;

import constants.Constants;
import domain.Ghost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GhostRepository {

    private List<Ghost> ghosts = new ArrayList<>();

    public GhostRepository(int numberOfGhosts) {
        this.createGhosts(numberOfGhosts);
    }

    public List<Ghost> getGhostList() {
        return this.ghosts;
    }

    public void createGhosts(int numberOfGhosts) {
        IntStream.range(0, numberOfGhosts)
                .forEach(i -> ghosts.add(new Ghost()));
    }

    public void moveUp(Ghost ghost) {
        int y = ghost.getY();
        ghost.setY(y - 60);
    }

    public void moveDown(Ghost ghost) {
        int y = ghost.getY();
        ghost.setY(y + 60);
    }

    public void moveLeft(Ghost ghost) {
        int x = ghost.getX();
        ghost.setX(x - 60);
    }

    public void moveRight(Ghost ghost) {
        int x = ghost.getX();
        ghost.setX(x + 60);
    }

}

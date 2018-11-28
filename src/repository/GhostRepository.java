package repository;

import constants.Constants;
import domain.Ghost;

public class GhostRepository {

    public GhostRepository() {
    }

    public void moveUp(Ghost ghost) {
        int y = ghost.getY();
        ghost.setY(y - Constants.cellSize);
    }

    public void moveDown(Ghost ghost) {
        int y = ghost.getY();
        ghost.setY(y + Constants.cellSize);
    }

    public void moveLeft(Ghost ghost) {
        int x = ghost.getX();
        ghost.setX(x - Constants.cellSize);
    }

    public void moveRight(Ghost ghost) {
        int x = ghost.getX();
        ghost.setX(x + Constants.cellSize);
    }

}

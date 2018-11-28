package repository;

import constants.Constants;
import domain.Pacman;

public class PacmanRepository {

    private Pacman pacman = Pacman.getInstance();

    public PacmanRepository() {
    }

    public void moveUp() {
        int y = pacman.getY();
        pacman.setY(y - Constants.cellSize);
    }

    public void moveDown() {
        int y = pacman.getY();
        pacman.setY(y + Constants.cellSize);
    }

    public void moveLeft() {
        int x = pacman.getX();
        pacman.setX(x - Constants.cellSize);
    }

    public void moveRight() {
        int x = pacman.getX();
        pacman.setX(x + Constants.cellSize);
    }

}

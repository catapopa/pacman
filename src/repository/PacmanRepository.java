package repository;

import domain.Pacman;

public class PacmanRepository {

    private Pacman pacman = Pacman.getInstance();

    public PacmanRepository() {
    }

    public void moveUp(){
        int y = pacman.getY();
        if(y != 0) {
            pacman.setY(y - 1);
        }
    }

    public void moveDown(){
        int y = pacman.getY();
        pacman.setY(y + 1);
    }

    public void moveLeft(){
        int x = pacman.getX();
        pacman.setX(x - 1);
    }

    public void moveRight(){
        int x = pacman.getX();
        pacman.setX(x + 1);
    }

}

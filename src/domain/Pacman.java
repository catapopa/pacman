package domain;

import domain.Cell;

public class Pacman {

    private int x;
    private int y;

    public Pacman() {
        Cell cell = new Cell(this.x, this.y, 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

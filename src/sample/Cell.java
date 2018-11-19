package sample;

import java.util.function.Consumer;

public class Cell{

    private int x;
    private int y;
    private int type;

    public Cell(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cell: " +
                "x = " + x +
                ", y = " + y +
                ", type = " + type;
    }
    //private Position position;
}

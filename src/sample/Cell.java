package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Cell {

    private int x;
    private int y;
    private int type;
    // 0 - road, 1 - wall, 2 - pacman, 3 - ghost
    //private enum Type {wall, road, pacman, ghost};
    //private Type type;
    public static int length = 50;

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


    public Rectangle drawCell() {

        Rectangle rectangle = null;

        if (this.type == 1) {
            rectangle = new Rectangle(this.x, this.y, 60, 60);
            rectangle.setFill(Color.BLACK);
        } else if (this.type == 0) {
            rectangle = new Rectangle(this.x, this.y, 60, 60);
            rectangle.setFill(Color.LAVENDER);
        }
        
        return rectangle;
    }

}

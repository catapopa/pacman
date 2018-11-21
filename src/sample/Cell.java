package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Cell {

    private int x;
    private int y;
    private int type;
    private Node node;
    // 0 - road, 1 - wall, 2 - pacman, 3 - ghost

    //private enum Type {wall, road, pacman, ghost};
    //private Type type;

    //public static int length = 60;

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

    public Node getNode(){
        return this.node;
    }

    public void setNode() {
        if (this.type == 1) {
            this.node = new Rectangle(this.x, this.y, 60, 60);
            ((Rectangle)node).setFill(Color.BLUEVIOLET);
        } else if (this.type == 0) {
            this.node = new Rectangle(this.x, this.y, 60, 60);
            ((Rectangle)node).setFill(Color.LAVENDER);
        }
    }

    @Override
    public String toString() {
        return "Cell: " +
                "x = " + x +
                ", y = " + y +
                ", type = " + type;
    }
}

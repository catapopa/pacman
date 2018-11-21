package domain;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class Cell {

    private int x;
    private int y;
    private Color color;
    private Node node;
    // 0 - road, 1 - wall, 2 - pacman, 3 - ghost

    public Cell(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return this.node;
    }

    public void setNode() {
        this.node = new Rectangle(this.x, this.y, 60, 60);
        ((Rectangle) node).setFill(this.color);
    }

    @Override
    public String toString() {
        return "Cell: " +
                "x = " + x +
                ", y = " + y +
                ", color = " + color;
    }
}

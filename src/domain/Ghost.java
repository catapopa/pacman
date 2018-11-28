package domain;

import javafx.scene.paint.Color;

public class Ghost extends Cell {


    public Ghost() {
        super(0, 0, Color.ORANGERED);
    }

    public void init(int x, int y) {
        super.setX(x);
        super.setY(y);
        super.getNode().relocate(x, y);
    }
}

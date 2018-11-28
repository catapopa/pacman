package domain;

import constants.Constants;
import javafx.scene.paint.Color;

public class Ghost extends Cell {

    private Constants.Direction direction;

    public Ghost() {
        super(0, 0, Color.ORANGERED);
        this.init(Constants.cellSize, Constants.cellSize);
    }

    public void init(int x, int y) {
        super.setX(x);
        super.setY(y);
        super.getNode().relocate(x, y);
    }

    public Constants.Direction getDirection() {
        return direction;
    }

    public void setDirection(Constants.Direction direction) {
        this.direction = direction;
    }
}

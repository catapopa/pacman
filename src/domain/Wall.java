package domain;

import javafx.scene.paint.Color;

public class Wall extends Cell {

    private boolean block;

    public Wall(int x, int y, boolean block) {
        super(x, y, Color.BLACK);
        this.block = block;
        Color color = getWallColor();
        super.setColor(color);
    }

    private Color getWallColor(){
        if (block){
            return Color.BLACK;
        }
        return Color.WHEAT;
    }

    public boolean isBlock() {
        return block;
    }
}

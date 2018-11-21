package domain;

import javafx.scene.paint.Color;

public class Pacman extends Cell {

    private static Pacman single_instance = null;

    private Pacman() {
        super(0,0, Color.YELLOW);
    }

    // static method to create instance of Singleton class
    public static Pacman getInstance() {
        if (single_instance == null)
            single_instance = new Pacman();

        return single_instance;
    }

    public void init(int x, int y){
        super.setX(x);
        super.setY(y);
        super.getNode().relocate(x, y);
    }
}

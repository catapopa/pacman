import constants.Constants;
import controller.PacmanController;
import controller.WallController;
import domain.Cell;
import domain.Pacman;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {
    Cell[][] wall;
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("P a c m a n");

        WallController wallController = new WallController();
        this.wall = wallController.getWallList();

        PacmanController pacmanController = new PacmanController(wallController);

        Pacman pacman = Pacman.getInstance();
        pacman.init(Constants.cellSize, Constants.cellSize);

        GridPane wallPane = drawWall();
        Group group = new Group(wallPane, pacman.getNode());
        Scene scene = new Scene(group, 600, 600);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
              switch (event.getCode()) {
                case UP:
                    pacmanController.moveUp();
                    break;
                case DOWN:
                    pacmanController.moveDown();
                    break;
                case LEFT:
                    pacmanController.moveLeft();
                    break;
                case RIGHT:
                    pacmanController.moveRight();
                    break;
            }
            this.draw();
        });
    }

    private void draw() {
        Pacman pacman = Pacman.getInstance();
        pacman.getNode().relocate(pacman.getX(), pacman.getY());
    }

    private GridPane drawWall(){
        GridPane root = new GridPane();
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> root.add(wall[i][j].getNode(), wall[i][j].getX(), wall[i][j].getY())));
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

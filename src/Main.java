import constants.Constants;
import controller.GhostController;
import controller.PacmanController;
import controller.WallController;
import domain.Cell;
import domain.Ghost;
import domain.Pacman;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import repository.GhostRepository;

import java.util.stream.IntStream;

public class Main extends Application {
    Cell[][] wall;
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {

        // wall
        WallController wallController = new WallController();
        this.wall = wallController.getWallList();
        // pacman
        PacmanController pacmanController = new PacmanController(wallController);
        Pacman pacman = Pacman.getInstance();
        pacman.init(Constants.cellSize, Constants.cellSize);
        // ghost
        int numberOfGhosts = 4;
        GhostRepository ghostRepository = new GhostRepository(numberOfGhosts);
        GhostController ghostController = new GhostController(ghostRepository, wallController, GhostController.SpeedOption.FAST);
        ghostController.start();

        // set window
        this.stage = primaryStage;
        stage.setTitle("P a c m a n");
        GridPane wallPane = drawWall();

        // TODO fantomele intra in zid in unele locuri; posibila problema: axele sunt inversate

        Group group = new Group(wallPane, pacman.getNode());
        IntStream.range(0, numberOfGhosts)
                .forEach(i -> group.getChildren().add(ghostRepository.getGhostList().get(i).getNode()));

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

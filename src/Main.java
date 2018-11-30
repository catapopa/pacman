import constants.Constants;
import constants.Constants.Direction;
import controller.GhostController;
import controller.PacmanController;
import repository.UserDatabase;
import controller.WallController;
import domain.Cell;
import domain.Pacman;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.stream.IntStream;

/*TODO
* choose speed, choose number of ghosts
* score - name, points
* */

public class Main extends Application {
    private Cell[][] wall;
    private Stage stage = new Stage();

    UserDatabase userdataBase = new UserDatabase();

    @Override
    public void start(Stage primaryStage) {

        // wall
        WallController wallController = new WallController();
        this.wall = wallController.getWallList();
        // ghost
        int numberOfGhosts = 3;
        GhostController ghostController = new GhostController(wallController, Constants.SpeedOption.MEDIUM, numberOfGhosts);
        ghostController.start();
        // pacman
        PacmanController pacmanController = new PacmanController(wallController, ghostController);
        Pacman pacman = Pacman.getInstance();
        pacman.init(Constants.cellSize, Constants.cellSize);
        // set window
        this.stage = primaryStage;
        stage.setTitle("P a c m a n");
        GridPane wallPane = drawWall();
        AnchorPane anchor = new AnchorPane(wallPane, pacman.getNode());
        IntStream.range(0, numberOfGhosts)
                .forEach(i -> anchor.getChildren().add(ghostController.getGhostList().get(i).getNode()));

        Scene scene = new Scene(anchor, 600, 600);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
              switch (event.getCode()) {
                case UP:
                    pacmanController.move(Direction.UP);
                    break;
                case DOWN:
                    pacmanController.move(Direction.DOWN);
                    break;
                case LEFT:
                    pacmanController.move(Direction.LEFT);
                    break;
                case RIGHT:
                    pacmanController.move(Direction.RIGHT);
                    break;
            }
        });
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

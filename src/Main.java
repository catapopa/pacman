import constants.Constants;
import constants.Constants.Direction;
import controller.GhostController;
import controller.PacmanController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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

import static constants.Constants.SpeedOption.FAST;
import static constants.Constants.SpeedOption.MEDIUM;
import static constants.Constants.SpeedOption.SLOW;

/*TODO
 * choose speed, choose number of ghosts
 * score - name, points
 * */

public class Main extends Application {
    private Cell[][] wall;
    private Stage stage = new Stage();
    private Scene choiceScene, gameScene, scoreScene;

    //UserDatabase userdataBase = new UserDatabase();

    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        stage.setTitle("P a c m a n");
        this.setChoiceScene();
        stage.show();
    }

    private void setChoiceScene() {
        // scene 1
        Label speedLabel = new Label("Choose difficulty");
        ChoiceBox<Constants.SpeedOption> speedChoice = new ChoiceBox<>();
        speedChoice.getItems().addAll(SLOW, MEDIUM, FAST);
        speedChoice.setValue(MEDIUM);

        Label ghostsLabel = new Label("Choose number of ghosts");
        ChoiceBox<Integer> ghostChoice = new ChoiceBox<>();
        ghostChoice.getItems().addAll(1, 2, 3, 4);
        ghostChoice.setValue(3);

        Label userNameLabel = new Label("Enter name below");
        TextField userName = new TextField();

        Button okButton = new Button("start!");
        okButton.setOnAction(e -> this.setGameScene(ghostChoice.getValue(), speedChoice.getValue()));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 30, 20, 20));
        layout.getChildren().addAll(speedLabel, speedChoice, ghostsLabel, ghostChoice, userNameLabel, userName, okButton);

        this.choiceScene = new Scene(layout, 600, 600);
        stage.setScene(this.choiceScene);
    }

    private void setGameScene(int numberOfGhosts, Constants.SpeedOption speedOption) {
        // scene 2

        // wall
        WallController wallController = new WallController();
        this.wall = wallController.getWallList();
        // ghost
        GhostController ghostController = new GhostController(wallController, speedOption, numberOfGhosts);
        ghostController.start();
        // pacman
        PacmanController pacmanController = new PacmanController(wallController, ghostController);
        Pacman pacman = Pacman.getInstance();
        pacman.init(Constants.cellSize, Constants.cellSize);

        GridPane wallPane = drawWall();
        AnchorPane anchor = new AnchorPane(wallPane, pacman.getNode());
        this.gameScene = new Scene(anchor, 600, 600);
        stage.setScene(this.gameScene);

        IntStream.range(0, numberOfGhosts)
                .forEach(i -> anchor.getChildren().add(ghostController.getGhostList().get(i).getNode()));

        gameScene.setOnKeyPressed(event -> {
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

    private GridPane drawWall() {
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

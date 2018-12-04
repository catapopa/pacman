import constants.Constants;
import controller.GhostController;
import controller.PacmanController;
import controller.WallController;
import domain.Cell;
import domain.Pacman;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.UserDatabase;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

import static constants.Constants.SpeedOption.FAST;
import static constants.Constants.SpeedOption.MEDIUM;
import static constants.Constants.SpeedOption.SLOW;

class View {

    private Stage stage;
    private TextField userNameTextFiled;
    private Cell[][] wall;
    private Timer timer;
    private GhostController ghostController;

    View(Stage stage) {
        this.stage = stage;
        this.setChoiceScene();
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
        userNameTextFiled = new TextField();

        Button okButton = new Button("start!");
        okButton.setOnAction(e -> this.setGameScene(ghostChoice.getValue(), speedChoice.getValue()));
        //okButton.setOnAction(e -> this.setScoreScene(0));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 30, 20, 20));
        layout.getChildren().addAll(speedLabel, speedChoice, ghostsLabel, ghostChoice, userNameLabel, userNameTextFiled, okButton);

        Scene choiceScene = new Scene(layout, 600, 600);
        stage.setScene(choiceScene);
        stage.show();
    }

    private void setGameScene(int numberOfGhosts, Constants.SpeedOption speedOption) {
        // scene 2

        // wall
        WallController wallController = new WallController();
        this.wall = wallController.getWallList();
        // ghost
        this.ghostController = new GhostController(wallController, speedOption, numberOfGhosts);
        ghostController.start();
        // pacman
        PacmanController pacmanController = new PacmanController(wallController, ghostController);
        Pacman pacman = Pacman.getInstance();
        pacman.init(Constants.cellSize, Constants.cellSize);

        GridPane wallPane = drawWall();
        AnchorPane anchor = new AnchorPane(wallPane, pacman.getNode());
        Scene gameScene = new Scene(anchor, 600, 600);
        stage.setScene(gameScene);

        IntStream.range(0, numberOfGhosts)
                .forEach(i -> anchor.getChildren().add(ghostController.getGhostList().get(i).getNode()));

        gameScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    pacmanController.move(Constants.Direction.UP);
                    break;
                case DOWN:
                    pacmanController.move(Constants.Direction.DOWN);
                    break;
                case LEFT:
                    pacmanController.move(Constants.Direction.LEFT);
                    break;
                case RIGHT:
                    pacmanController.move(Constants.Direction.RIGHT);
                    break;
            }
        });

        this.timer = new Timer();
        this.start();
    }

    private void setScoreScene(Integer points) {
        // scene 3
        ObservableList<User> userObservableList = FXCollections.observableArrayList();

        UserDatabase userDatabase = new UserDatabase();
        User user = new User(userNameTextFiled.getText(), points, String.valueOf(new Date()));
        userDatabase.addToDatabase(user);
        userDatabase.getFromDatabase();

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        TableColumn<User, Integer> scoreColumn = new TableColumn<>("Score");
        TableColumn<User, Date> dateColumn = new TableColumn<>("Date");
        nameColumn.setMinWidth(200);
        scoreColumn.setMinWidth(200);
        dateColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        System.out.println("column");
        TableView users = new TableView();

        userObservableList.addAll(userDatabase.getUsers());
        users.setItems(userObservableList);
        users.getColumns().addAll(nameColumn, scoreColumn, dateColumn);
        System.out.println("list");

        Scene scoreScene = new Scene(users, 600, 600);
        stage.setScene(scoreScene);
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            final Integer[] points = {0};

            points[0] += 1;
            if (!ghostController.getIsAlive()) {
                this.cancel();
                setScoreScene(points[0]);
            }
        }
    };

    private void start() {
        this.timer.scheduleAtFixedRate(this.timerTask, 1000, 1000);
    }

    private GridPane drawWall() {
        GridPane root = new GridPane();
        IntStream.range(0, 10)
                .forEach(i -> IntStream.range(0, 10)
                        .forEach(j -> root.add(wall[i][j].getNode(), wall[i][j].getX(), wall[i][j].getY())));
        return root;
    }

}

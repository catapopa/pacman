import constants.Constants;
import constants.Constants.Direction;
import controller.GhostController;
import controller.PacmanController;
import controller.WallController;
import domain.Cell;
import domain.Pacman;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.IntStream;

/*TODO
* collision pacman - ghost
* choose speed, choose number of ghosts
* pacdots
* score - name, points
* */

public class Main extends Application {
    Cell[][] wall;
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {


        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    Constants.DB_URL, Constants.DatabaseUser, Constants.DatabasePassword);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Select...");
            stmt = conn.createStatement();

            String sql = "Select * from score";

            stmt.executeQuery(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try




        // wall
        WallController wallController = new WallController();
        this.wall = wallController.getWallList();
        // pacman
        PacmanController pacmanController = new PacmanController(wallController);
        Pacman pacman = Pacman.getInstance();
        pacman.init(Constants.cellSize, Constants.cellSize);
        // ghost
        int numberOfGhosts = 4;
        GhostController ghostController = new GhostController(wallController, Constants.SpeedOption.FAST, numberOfGhosts);
        ghostController.start();

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

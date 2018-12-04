import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage = new Stage();


    @Override
    public void start(Stage primaryStage) {

        stage.setTitle("P a c m a n");
        View view = new View(primaryStage);
        //stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

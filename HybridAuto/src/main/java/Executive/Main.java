package Executive;

import Utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        stage.setTitle("Hybrid AutoTech");
        stage.setScene(Constants.scene);
        stage.setOnCloseRequest(e->{
            System.exit(1);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

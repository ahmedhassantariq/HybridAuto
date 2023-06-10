package Executive;

import Functionality.Database.DB.DatabaseConnector;
import Utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        DatabaseConnector.connect();
        Main.stage = stage;
        stage.setTitle("Hybrid AutoTech");
//        stage.setResizable(false);
        stage.setScene(Constants.scene);
        stage.setOnCloseRequest(e->{

        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

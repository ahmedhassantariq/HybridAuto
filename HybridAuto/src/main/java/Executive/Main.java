package Executive;

import Utils.Constants;
import Utils.FileManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        stage.setTitle("Hybrid AutoTech");
        stage.setScene(Constants.scene);
//        FileManager.writeSettings();
        FileManager.readSettings();
        stage.setOnCloseRequest(e->{
            System.exit(1);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

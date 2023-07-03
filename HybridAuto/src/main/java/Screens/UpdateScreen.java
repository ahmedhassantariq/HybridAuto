package Screens;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UpdateScreen {
    public static ProgressBar progressBar = new ProgressBar(0.0);



    public static void updateScreen(){
        Label label = new Label("Downloading Update");
        Label label1 = new Label("Do not ShutDown");
        VBox vBox = new VBox(label, label1, progressBar);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(vBox,400,200));
        stage.show();
    }

}

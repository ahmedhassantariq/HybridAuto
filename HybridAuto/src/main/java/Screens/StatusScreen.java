package Screens;

import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class StatusScreen {
    public static Parent statusScreen(){
        HBox statusBox = new HBox();
        statusBox.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(0,15,0,0,false),null)));
        statusBox.setMinHeight(40);

        return statusBox;
    }
}

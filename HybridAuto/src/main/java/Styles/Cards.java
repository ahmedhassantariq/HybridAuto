package Styles;

import Utils.Formatter;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Cards {
    public static Parent card(String head,String val,String foot){
        Label header = Labels.titleLabel(head);
        Label value = Labels.cardLabel(val);
        Label footer = Labels.titleLabel(foot);

        VBox cardBox = new VBox(header,value,footer);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPrefSize(200,200);
        cardBox.setMaxSize(200,200);
        cardBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7,7,7,7,false),null)));
        return cardBox;
    }
}

package Screens;

import Styles.Labels;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class StatusScreen {
    private static Label dateTimeLabel = Labels.timeLabel();
    private static Label notificationLabel = Labels.notificationLabel("NOTIFICATIONS");
    private static LocalTime currentTime = LocalTime.now();
    private static LocalTime previousTime = LocalTime.now();
    public static Parent statusScreen(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateDateTime();
            notification();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();




        HBox notificationBox = new HBox(notificationLabel);
        notificationBox.setAlignment(Pos.CENTER);
        notificationBox.setMaxSize(200,40);
        notificationBox.setMinWidth(200);
        notificationBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,15,15,false),null)));


        HBox statusBox = new HBox(dateTimeLabel,notificationBox);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        statusBox.setSpacing(5);
        statusBox.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(0,15,0,0,false),null)));
        statusBox.setMinHeight(40);




        return statusBox;
    }
    public static void updateDateTime(){
        dateTimeLabel.setText(
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+
                        " "+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"))
        );
    }

    public static void setNotification(String text){
        previousTime = LocalTime.now();
        notificationLabel.setText(text);
    }

    public static void notification(){
        currentTime = LocalTime.now();
        java.time.Duration duration = java.time.Duration.between(previousTime,currentTime);
        long seconds = duration.get(ChronoUnit.SECONDS);
        if(seconds>3){
            notificationLabel.setText("");
        }
    }
}

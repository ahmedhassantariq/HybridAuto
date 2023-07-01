package Screens;

import Styles.Colors;
import Styles.Labels;
import Utils.Internet;
import Utils.Notification;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.ligaturesymbols.LigatureSymbols;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

public class StatusScreen {
    private static Label dateTimeLabel = Labels.timeLabel();
    private static Label notificationSymbol = Labels.notificationSymbol("",FontIcon.of(LigatureSymbols.BELL));
    private static Label settingsSymbol = Labels.notificationSymbol("",FontIcon.of(LigatureSymbols.SETTING));
    private static Label colorTheme = Labels.notificationSymbol("",FontIcon.of(LigatureSymbols.DARK));
    private static Label connectivitySymbol = Labels.notificationSymbol("",FontIcon.of(PrestaShopIcons.BROKEN_LINK));



    private static ContextMenu contextMenu = new ContextMenu();
    private static Stack<String> notificationStack = new Stack<>();

    public static Parent statusScreen(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateDateTime();
            checkInternet();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //ToolTips
        notificationSymbol.setTooltip(Labels.tooltip("Messages"));
        settingsSymbol.setTooltip(Labels.tooltip("Settings"));
        colorTheme.setTooltip(Labels.tooltip("Change Color"));



        notificationSymbol.setOnMouseClicked(e->{
            contextMenu.getItems().clear();
            MenuItem cleaMenuItem = new MenuItem("Clear All");
            if(!notificationStack.isEmpty()) {
                contextMenu.getItems().add(cleaMenuItem);
            }
            cleaMenuItem.setOnAction(actionEvent->{
                notificationStack.clear();
            });
            for(int i=0;i<notificationStack.size();i++){
                contextMenu.getItems().add(new MenuItem(notificationStack.get(i)));
            }
            contextMenu.show(notificationSymbol, Side.RIGHT,0,0);
        });


        colorTheme.setOnMouseClicked(e->{

        });

        HBox statusBox = new HBox(dateTimeLabel, notificationSymbol, settingsSymbol,colorTheme, connectivitySymbol);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        statusBox.setSpacing(10);
        statusBox.setBackground(new Background(new BackgroundFill(Colors.statusBoxColor,new CornerRadii(0,15,0,0,false),null)));
        statusBox.setMinHeight(45);
        statusBox.setMaxHeight(45);

        return statusBox;
    }
    public static void updateDateTime(){
        dateTimeLabel.setText(
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+
                        " "+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"))
        );
    }

    public static void setNotification(String text){
        new Notification(text);
        notificationStack.push(text);
    }
    public static void checkInternet() {

        if(Internet.isConnected()){
            FontIcon fontIcon = new FontIcon(PrestaShopIcons.LINK);
            fontIcon.setIconColor(Colors.notificationIconColor);
            fontIcon.setIconSize(32);
            connectivitySymbol.setGraphic(fontIcon);
            connectivitySymbol.setTooltip(Labels.tooltip("Connected"));
        }else {
            FontIcon fontIcon = new FontIcon(PrestaShopIcons.BROKEN_LINK);
            fontIcon.setIconColor(Colors.notificationIconColor);
            fontIcon.setIconSize(32);
            connectivitySymbol.setGraphic(fontIcon);
            connectivitySymbol.setTooltip(Labels.tooltip("Disconnected"));

        }
    }

}

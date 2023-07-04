package Screens;

import Styles.Colors;
import Styles.Icons;
import Styles.Labels;
import Utils.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Stack;

public class StatusScreen {
    private static Label dateTimeLabel = Labels.timeLabel();
    private static Label notificationSymbol = Labels.notificationSymbol("", Icons.messages);
    public static Label settingsSymbol = Labels.notificationSymbol("",Icons.settings);
    private static Label connectivitySymbol = Labels.notificationSymbol("",Icons.wifi_OFF);
    private static Label receiptSymbol = Labels.notificationSymbol("",Icons.receipts);
    private static Color notificationIconColor = Color.WHITE;

    private static ContextMenu contextMenu = new ContextMenu();
    private static ContextMenu receiptMenu = new ContextMenu();
    private static Stack<String> notificationStack = new Stack<>();

    public static Parent statusScreen(){
        updateDateTime();
        checkInternet();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
            updateDateTime();
            checkInternet();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //ToolTips
        notificationSymbol.setTooltip(Labels.tooltip("Messages"));
        settingsSymbol.setTooltip(Labels.tooltip("Settings"));
        receiptSymbol.setTooltip(new Tooltip("Receipts"));



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





        receiptSymbol.setOnMouseClicked(e->{
            receiptMenu.getItems().clear();
            String folderPath = "C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Receipts";
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        MenuItem menuItem = new MenuItem(file.getName());
                        receiptMenu.getItems().add(menuItem);
                        menuItem.setOnAction(ep->{
                            PDFDocument.show(file.getName());
                        });
                    }
                }
            }
            receiptMenu.show(receiptSymbol, Side.RIGHT,0,0);
        });

        HBox statusBox = new HBox(dateTimeLabel, notificationSymbol, settingsSymbol,receiptSymbol, connectivitySymbol);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        statusBox.setSpacing(10);
        statusBox.setBackground(new Background(new BackgroundFill(Colors.statusBoxColor,new CornerRadii(0,15,0,0,false),null)));
        statusBox.setMinHeight(45);
        statusBox.setMaxHeight(45);
        return statusBox;
    }
    public static void updateDateTime(){
        dateTimeLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+
                        " "+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"))
        );

    }

    public static void setNotification(String text){
        new Notification(text);
        notificationStack.push(text);
    }
    public static void checkInternet() {
        long time = ChronoUnit.MINUTES.between(Constants.settings.getLocalDateTime(),LocalDateTime.now());
        if(time>2){
            Constants.settings.setLocalDateTime(LocalDateTime.now());
        if(Internet.isConnected()){
            FontIcon fontIcon = Icons.wifi_ON;
            fontIcon.setIconColor(notificationIconColor);
            fontIcon.setIconSize(32);
            connectivitySymbol.setGraphic(fontIcon);
            connectivitySymbol.setTooltip(Labels.tooltip("Connected"));
        }else {
            FontIcon fontIcon = Icons.wifi_OFF;
            fontIcon.setIconColor(notificationIconColor);
            fontIcon.setIconSize(32);
            connectivitySymbol.setGraphic(fontIcon);
            connectivitySymbol.setTooltip(Labels.tooltip("Disconnected"));

        }
        }
    }


}

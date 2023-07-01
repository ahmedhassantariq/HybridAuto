package Styles;

import javafx.scene.paint.Color;

public class Colors {

    private static Color whiteColor = Color.WHITE;
    private static Color mainBackgroundColor = Color.web("#e9e8e8");

    public static Color buttonColor = Color.web("#02557a");
    public static Color fontIconColor = whiteColor;
    public static Color buttonTextColor = whiteColor;
    public static Color mouseFontIconColor = Color.web("#02557a");
    public static Color mouseButtonTextColor = Color.web("#02557a");
    public static Color darkButtonColor = Color.web("#02557a");


    public static Color fieldBoxColor = whiteColor;
    public static Color statusBoxColor = Color.web("#02557a");
    public static Color categoryBoxColor = whiteColor;
    public static Color cartBoxColor = whiteColor;
    public static Color itemBoxColor = whiteColor;
    public static Color customerBoxColor = whiteColor;
    public static Color productBoxColor = whiteColor;
    public static Color cardBoxBorderColor = Color.web("#dcdcdc");
    public static Color cardBoxColor = whiteColor;
    public static Color detailBoxColor = whiteColor;;


    public static Color mainPaneColor = mainBackgroundColor;

    public static Color avatarBoxColor = Color.web("#02557a");


    public static Color titleLabelColor = Color.BLACK;
    public static Color cardLabelColor = Color.BLACK;
    public static Color checkoutLabelColor = Color.BLACK;
    public static Color timeLabelColor = whiteColor;

    public static Color notificationIconColor = whiteColor;



    public static Color productBoxBorderColor = Color.web("02557a");
    public static Color categoryBoxBorderColor = Color.web("02557a");


    public static void setDarkMode(){
        if(mainPaneColor == mainBackgroundColor)
            mainPaneColor = Color.BLACK;
        else
            mainPaneColor = mainBackgroundColor;
    }
}

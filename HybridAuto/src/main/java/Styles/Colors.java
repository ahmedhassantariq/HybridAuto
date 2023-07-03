package Styles;

import javafx.scene.paint.Color;

public class Colors {

    public static Color fieldBoxColor = Color.WHITE;;
    public static Color statusBoxColor = Color.web("#02557a");
    public static Color categoryBoxColor = Color.WHITE;;
    public static Color cartBoxColor = Color.WHITE;;
    public static Color itemBoxColor = Color.WHITE;;
    public static Color customerBoxColor = Color.WHITE;;
    public static Color productBoxColor = Color.WHITE;;


    public static Color mainPaneColor = Color.web("#e8e8e8");
    public static Color avatarBoxColor = Color.web("#02557a");







    public static Color productBoxBorderColor = Color.web("02557a");
    public static Color categoryBoxBorderColor = Color.web("02557a");


    public static void setDarkMode(){
        if(mainPaneColor == Color.web("#e9e8e8"))
            mainPaneColor = Color.BLACK;
        else
            mainPaneColor = Color.web("#e9e8e8");
    }
}

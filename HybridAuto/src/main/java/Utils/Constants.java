package Utils;

import Executive.Main;
import Functionality.Data.VersionControl;
import Screens.LoginForm;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.sql.SQLException;

public class Constants {
    public static VersionControl settings;
    public static final double screenHeight = 600;
    public static final double screenWidth = 1100;
    public static final double formHeight = 400;
    public static final double formWidth = 500;
    public static String logInUsername = "ADMIN";


    public static Scene scene;

    static {
        try {
            scene = new Scene(LoginForm.loginForm(),screenWidth,screenHeight);
        } catch (SQLException | ClassNotFoundException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static void setScene(Parent view) {
        //don't change scene if already there
        if(scene.getRoot() == view) return;
        scene = new Scene(view,screenWidth,screenHeight);
        Main.stage.setScene(scene);
    }


    public static void logout() throws SQLException, ClassNotFoundException {
        setScene(LoginForm.loginForm());
    }

}

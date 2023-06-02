package Utils;

import Executive.Main;
import Screens.LoginForm;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class Constants {
    public static final double screenHeight = 600;
    public static final double screenWidth = 1100;

    public static final double formHeight = 400;
    public static final double formWidth = 500;


    public static Scene scene;

    static {
        try {
            scene = new Scene(LoginForm.loginForm(),screenWidth,screenHeight);
        } catch (SQLException | ClassNotFoundException e) {
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

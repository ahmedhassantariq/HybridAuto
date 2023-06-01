package Screens;

import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import io.github.palexdev.materialfx.css.themes.Themes;
import io.github.palexdev.materialfx.enums.ButtonType;
import io.github.palexdev.materialfx.skins.MFXTextFieldSkin;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.ligaturesymbols.LigatureSymbols;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

import java.io.IOException;
import java.sql.SQLException;

public class LoginForm {
    private static Label alertLabel = new Label();
    public static Parent loginForm() throws SQLException, ClassNotFoundException {


        GridPane gridPane = new GridPane();
        gridPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //InputFields
        MFXTextField username = new MFXTextField();
        username.setAlignment(Pos.CENTER);
        username.setPrefSize(200,10);
        username.setFloatingText("Username");

        MFXPasswordField passwordField = new MFXPasswordField();
        passwordField.setAlignment(Pos.CENTER);
        passwordField.setPrefSize(200,10);
        passwordField.setFloatingText("Password");
        //Buttons
        MFXButton loginButton = new MFXButton("Login");
        loginButton.setBackground(new Background(new BackgroundFill(Color.web("#012231"),new CornerRadii(15,15,15,15,false),null)));
        loginButton.setPrefSize(200,40);
        loginButton.setAlignment(Pos.CENTER);
        loginButton.setTextFill(Color.WHITE);
        Font buttonFont = Font.font("Cooper",18);
        loginButton.setFont(buttonFont);


        alertLabel.setVisible(false);

        //Adding Field to GridPane
        gridPane.add(username,0,1);
        gridPane.add(passwordField,0,3);
        gridPane.add(alertLabel,0,4);
        gridPane.add(loginButton,0,5);



        //Fields and Button Functionality

        EventHandler<KeyEvent> entryFieldKeyHandler= keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                try {
                    login(username.getText(),passwordField.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        username.addEventHandler(KeyEvent.KEY_PRESSED,entryFieldKeyHandler);
        passwordField.addEventHandler(KeyEvent.KEY_PRESSED,entryFieldKeyHandler);

        loginButton.setOnAction(e->{
            try {
                login(username.getText(),passwordField.getText());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return gridPane;
    }

    private static void login(String userName, String password) throws SQLException, ClassNotFoundException, IOException {
        Constants.setScene(MainScreen.mainScreen());
    }
}

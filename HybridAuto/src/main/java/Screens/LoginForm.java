package Screens;

import Styles.Buttons;
import Styles.Fields;
import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
        MFXTextField usernameField = Fields.textField("UserName",200,50);
        MFXTextField passwordField = Fields.passwordField("Password",200,50);
        //Buttons
        MFXButton loginButton = Buttons.FunctionButton("Login",200,40);


        alertLabel.setVisible(false);

        //Adding Field to GridPane
        gridPane.add(usernameField,0,1);
        gridPane.add(passwordField,0,3);
        gridPane.add(alertLabel,0,4);
        gridPane.add(loginButton,0,5);



        //Fields and Button Functionality

        EventHandler<KeyEvent> entryFieldKeyHandler= keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                try {
                    login(usernameField.getText(),passwordField.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        usernameField.addEventHandler(KeyEvent.KEY_PRESSED,entryFieldKeyHandler);
        passwordField.addEventHandler(KeyEvent.KEY_PRESSED,entryFieldKeyHandler);

        loginButton.setOnAction(e->{
            try {
                login(usernameField.getText(),passwordField.getText());
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

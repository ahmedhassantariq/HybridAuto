package Screens;

import Functionality.Database.DB.Firebase;
import Styles.Buttons;
import Styles.Colors;
import Styles.Fields;
import Styles.Icons;
import Utils.Constants;
import Utils.Notification;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class LoginForm {
    public static Parent loginForm() throws SQLException, ClassNotFoundException {
        VBox mainPane = new VBox();
        mainPane.setBackground(new Background(new BackgroundFill(Colors.mainPaneColor,new CornerRadii(0,0,0,0,false),null)));
        mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());

        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(10);

        //InputFields
        MFXTextField usernameField = Fields.textField("Email",200,50);
        MFXTextField passwordField = Fields.passwordField("Code",200,50);
        //Buttons
        MFXButton loginButton = Buttons.iconButton("Login",200,40, Icons.logIn);
        MFXButton sendCodeButton = Buttons.iconButton("Send Code",200,40, Icons.sendCode);
            mainPane.getChildren().addAll(usernameField,passwordField,sendCodeButton);
            passwordField.setDisable(true);




        //Fields and Button Functionality
        EventHandler<KeyEvent> userNameKeyHandler= keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                //Temporary
//                try {
//                    Constants.setScene(MainScreen.mainScreen());
//                } catch (SQLException | ClassNotFoundException ex) {
//                    new Notification(ex);
//                    throw new RuntimeException(ex);
//                }

                if(!usernameField.getText().isEmpty()&&!usernameField.getText().isBlank()){
                    if(Firebase.userAuth(usernameField.getText())){
                        passwordField.setDisable(false);
                        if(!mainPane.getChildren().contains(loginButton)){
                            mainPane.getChildren().remove(sendCodeButton);
                            mainPane.getChildren().add(loginButton);
                        }
                    }
                }
            }
        };
        EventHandler<KeyEvent> passwordKeyHandler= keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                if(!passwordField.getText().isEmpty()&&!passwordField.getText().isBlank()){
                    Firebase.codeAuth(passwordField.getText());
                }
            }
        };


        usernameField.addEventHandler(KeyEvent.KEY_PRESSED,userNameKeyHandler);
        passwordField.addEventHandler(KeyEvent.KEY_PRESSED,passwordKeyHandler);

        sendCodeButton.setOnAction(e-> {

//                    try {
//                        Constants.setScene(MainScreen.mainScreen());
//                    } catch (SQLException | ClassNotFoundException ex) {
//                        new Notification(ex);
//                        throw new RuntimeException(ex);
//                    }
            if(!usernameField.getText().isEmpty()&&!usernameField.getText().isBlank()){
                if(Firebase.userAuth(usernameField.getText())){
                    passwordField.setDisable(false);
                    mainPane.getChildren().remove(sendCodeButton);
                    mainPane.getChildren().add(loginButton);
                }
            }
                });


        loginButton.setOnAction(e->{
                if(!passwordField.getText().isEmpty()&&!passwordField.getText().isBlank()){
                    Firebase.codeAuth(passwordField.getText());
                }
        });
        return mainPane;
    }
}

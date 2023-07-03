package Screens;

import Entities.Services;
import Functionality.Forms.ServicesController;
import Styles.Buttons;
import Styles.Colors;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import Utils.ServiceTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class ServicesForm {
    private static VBox mainPane;
    public static Parent servicesForm() throws SQLException {
        if(mainPane!=null){
            ServicesController.getServicesList();
            return mainPane;
        }

        MFXTextField searchField = Fields.textField("Search by Phone", 300, 40);
        searchField.delegateSetTextFormatter(Formatter.phoneFormatter());
        MFXButton viewDetailsButton = Buttons.FunctionButton("View Details", 150, 40);


        HBox fieldBox = new HBox(searchField, viewDetailsButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Colors.fieldBoxColor, new CornerRadii(15, 15, 15, 15, false), null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);


        StackPane borderContainer = new StackPane();
        ServicesController.getServicesList();
        borderContainer.getChildren().add(ServiceTable.serviceTable());


        searchField.textProperty().addListener(e -> {
            ServicesController.searchOrder(new Services(null,null,null,null,searchField.getText(),null,null,null,null));
        });
        viewDetailsButton.setOnAction(e->{
            if(ServiceTable.serviceTable.getSelectionModel().getSelectedItem()!=null){
                OrderDetailScreen.showDetails(ServiceTable.serviceTable.getSelectionModel().getSelectedItem());
            }
        });
        ServiceTable.serviceTable.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
            if(ServiceTable.serviceTable.getSelectionModel().getSelectedItem()!=null){
                OrderDetailScreen.showDetails(ServiceTable.serviceTable.getSelectionModel().getSelectedItem());
            }
            }
        });


        mainPane = new VBox(Labels.titleLabel("Services"), fieldBox, borderContainer);
        mainPane.setBackground(new Background(new BackgroundFill(Colors.mainPaneColor,new CornerRadii(0,0,0,0,false),null)));
        mainPane.setPadding(new Insets(10, 0, 0, 0));
        mainPane.setSpacing(10);


        Platform.runLater(() -> {
            mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.TABLE_VIEW.loadTheme());
        });

        return mainPane;
    }
}

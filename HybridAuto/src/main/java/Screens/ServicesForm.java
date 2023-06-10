package Screens;

import Entities.Services;
import Entities.Stock;
import Functionality.Forms.InventoryController;
import Functionality.Forms.ServicesController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import Utils.InventoryTable;
import Utils.ServiceTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class ServicesForm {
    public static Parent servicesForm() throws SQLException {

        MFXTextField searchField = Fields.textField("Search", 300, 40);
        searchField.delegateSetTextFormatter(Formatter.phoneFormatter());
        MFXButton createCategoryButton = Buttons.FunctionButton("View Details", 150, 40);


        HBox fieldBox = new HBox(searchField, createCategoryButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);


        StackPane borderContainer = new StackPane();
        ServicesController.getServicesList();
        borderContainer.getChildren().add(ServiceTable.serviceTable());


        searchField.textProperty().addListener(e -> {
            ServicesController.searchOrder(new Services(null,null,null,null,searchField.getText(),null,null,null,null));
        });




        VBox mainPane = new VBox(Labels.titleLabel("Services"), fieldBox, borderContainer);
        mainPane.setPadding(new Insets(10, 0, 0, 0));
        mainPane.setSpacing(10);


        Platform.runLater(() -> {
            mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.TABLE_VIEW.loadTheme());
        });

        return mainPane;
    }
}

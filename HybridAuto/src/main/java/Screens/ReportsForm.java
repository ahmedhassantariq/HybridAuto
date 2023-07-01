package Screens;

import Styles.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class ReportsForm {
    private static VBox mainPane;
    public static Parent reportsForm() throws SQLException {
        if(mainPane!=null){
            return mainPane;
        }
        MFXButton createCategoryButton = Buttons.FunctionButton("Customize",150,40);
        MFXButton addNewProductButton = Buttons.FunctionButton("Search",100,40);
        MFXButton editProductButton = Buttons.FunctionButton("Details",100,40);
        MFXButton deleteProductButton = Buttons.FunctionButton("Views",100,40);


        HBox fieldBox = new HBox(createCategoryButton,addNewProductButton,editProductButton,deleteProductButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Colors.fieldBoxColor,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);


        HBox chartContainer = new HBox(Charts.lineChart(),Charts.barChart(),Charts.pieChart());

        mainPane = new VBox(Labels.titleLabel("Reports"),fieldBox,chartContainer);
        mainPane.setBackground(new Background(new BackgroundFill(Colors.mainPaneColor,new CornerRadii(0,0,0,0,false),null)));
        mainPane.setPadding(new Insets(10,0,0,0));
        mainPane.setSpacing(10);


        return mainPane;
    }
}

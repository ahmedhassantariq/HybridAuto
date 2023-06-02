package Utils;

import Entities.Product;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TableView {
    private static VBox tableBox;
    private static HBox columnHeading;
    private static ObservableList<Product> observableList;
    public TableView(String tableName){
        tableBox = new VBox();
        tableBox.setAlignment(Pos.TOP_CENTER);
        tableBox.setMinSize(200,400);
        HBox tableTitle = new HBox(new Label(tableName));
        tableTitle.setBackground(new Background(new BackgroundFill(Color.BLUE,new CornerRadii(   0,0,0,0,false),null)));
        tableTitle.setAlignment(Pos.CENTER);
        tableTitle.setPadding(new Insets(10));
        columnHeading = new HBox();
        columnHeading.setBackground(new Background(new BackgroundFill(Color.GRAY,new CornerRadii(0,0,0,0,false),null)));
        columnHeading.setAlignment(Pos.CENTER_LEFT);
        columnHeading.setSpacing(10);
        tableBox.getChildren().addAll(tableTitle,columnHeading);
    }


    public Parent tableView(){
        return tableBox;
    }

    public void addColumn(String label){
        Label columnLabel = new Label(label);
        columnLabel.setBorder(Border.stroke(Color.web("#dcdcdc")));
        columnLabel.setPadding(new Insets(10));
        columnHeading.getChildren().add(columnLabel);
    }

}

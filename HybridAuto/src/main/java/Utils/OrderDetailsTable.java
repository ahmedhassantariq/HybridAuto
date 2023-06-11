package Utils;

import Entities.Stock;
import Functionality.Forms.ServicesController;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;

import java.sql.SQLException;

public class OrderDetailsTable {
    public static javafx.scene.control.TableView<Stock> detailTable;

    public static Parent orderDetailsTable() {

        detailTable = new TableView<>();
        detailTable.setBorder(Border.EMPTY);
        detailTable.setEditable(false);
        detailTable.setPrefHeight(200);
        detailTable.setItems(ServicesController.orderDetailList);
//        tableView.setBackground(new Background(new BackgroundFill(Color.BLUE,new CornerRadii(   15,15,15,15,false),null)));

        //Description Cell

        TableColumn<Stock, String> stockID = new TableColumn("Stock-ID");
        stockID.setCellValueFactory(cellData -> cellData.getValue().stockIDProperty());
        stockID.setMinWidth(50);
        stockID.setResizable(false);
//        inventoryProductIDCol.setStyle("-fx-background-color: #02557a");

        //Type Cell
        TableColumn<Stock,String> makeCol = new TableColumn<>("Make");
        makeCol.setCellValueFactory(cellData -> cellData.getValue().getMakeProperty());
        makeCol.setMinWidth(50);

        TableColumn<Stock,String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
        modelCol.setMinWidth(50);

        TableColumn<Stock,String> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(cellData -> cellData.getValue().getYearProperty());
        yearCol.setMinWidth(50);

        TableColumn<Stock,String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().productCategoryProperty());
        categoryCol.setMinWidth(50);

        //Amount Cell
        TableColumn<Stock,String> serialNumberCol = new TableColumn<>("Serial");
        serialNumberCol.setCellValueFactory(cellData -> cellData.getValue().serialNumberProperty());
        serialNumberCol.setMinWidth(50);

        TableColumn<Stock,String> costCol = new TableColumn<>("Cost");
        costCol.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        costCol.setMinWidth(50);

        TableColumn<Stock,String> conditionCol = new TableColumn<>("Condition");
        conditionCol.setCellValueFactory(cellData -> cellData.getValue().conditionProperty());
        conditionCol.setMinWidth(50);


        TableColumn<Stock,String> commentsCol = new TableColumn<>("Comments");
        commentsCol.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
        commentsCol.setMinWidth(50);



        detailTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        detailTable.getColumns().addAll(stockID,makeCol,modelCol,yearCol,categoryCol,serialNumberCol,costCol,conditionCol,commentsCol);



        ScrollPane scrollPane = new ScrollPane(detailTable);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(detailTable.getPrefWidth());
        return scrollPane;
    }
}

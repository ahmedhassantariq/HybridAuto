package Utils;

import Entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class SaleTable {
    public static TableView<Product> tableView;

    public static Parent saleTable() {
        tableView = new TableView<>();
        tableView.setBorder(Border.EMPTY);
        tableView.setEditable(false);
//        tableView.setBackground(new Background(new BackgroundFill(Color.BLUE,new CornerRadii(   15,15,15,15,false),null)));

        //Description Cell

        TableColumn<Product, String> inventoryProductIDCol = new TableColumn("ID");
        inventoryProductIDCol.setCellValueFactory(cellData -> cellData.getValue().inventoryProductIDProperty());
        inventoryProductIDCol.setMinWidth(50);
        inventoryProductIDCol.setResizable(false);
//        inventoryProductIDCol.setStyle("-fx-background-color: #02557a");
        //QTY Cell
        TableColumn<Product, String> carIDCol = new TableColumn("Car");
        carIDCol.setCellValueFactory(cellData -> cellData.getValue().carIDProperty());
        carIDCol.setMinWidth(50);

        //Type Cell
        TableColumn<Product,String> productIDCol = new TableColumn<>("Product");
        productIDCol.setCellValueFactory(cellData -> cellData.getValue().productIDProperty());
        productIDCol.setMinWidth(50);


        //Amount Cell
        TableColumn<Product,String> serialNumberCol = new TableColumn<>("Serial");
        serialNumberCol.setCellValueFactory(cellData -> cellData.getValue().serialNumberProperty());
        serialNumberCol.setMinWidth(50);

        TableColumn<Product,String> costCol = new TableColumn<>("Amount");
        costCol.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        costCol.setMinWidth(50);



        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(inventoryProductIDCol,carIDCol,productIDCol,serialNumberCol,costCol);





        ScrollPane scrollPane = new ScrollPane(tableView);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(tableView.getPrefWidth());

        ObservableList<Product> observableList = FXCollections.observableArrayList(
                new Product(null,null,null,null,null,null,null),
                new Product(null,null,null,null,null,null,null),
                new Product(null,null,null,null,null,null,null),
                new Product(null,null,null,null,null,null,null)

                );
        tableView.setItems(observableList);
        return scrollPane;
    }
}

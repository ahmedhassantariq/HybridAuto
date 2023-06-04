package Utils;

import Entities.Product;
import Functionality.Forms.InventoryController;
import Functionality.Forms.OrdersController;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;

import java.sql.SQLException;

public class OrderTable {
    public static TableView<Product> tableView;

    public static Parent orderTable() {

        tableView = new TableView<>();
        tableView.setBorder(Border.EMPTY);
        tableView.setEditable(false);
        tableView.setItems(OrdersController.orderList);
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
        return scrollPane;
    }
}

package Utils;

import Entities.Stock;
import Functionality.Forms.OrdersController;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;

public class CartTable {
    public static TableView<Stock> cartTable;

    public static Parent orderTable() {

        cartTable = new TableView<>();
        cartTable.setBorder(Border.EMPTY);
        cartTable.setEditable(false);
        cartTable.setItems(OrdersController.orderList);
//        tableView.setBackground(new Background(new BackgroundFill(Color.BLUE,new CornerRadii(   15,15,15,15,false),null)));

        //Description Cell

        TableColumn<Stock, String> inventoryProductIDCol = new TableColumn("Make");
        inventoryProductIDCol.setCellValueFactory(cellData -> cellData.getValue().getMakeProperty());
        inventoryProductIDCol.setMinWidth(50);
        inventoryProductIDCol.setResizable(false);
//        inventoryProductIDCol.setStyle("-fx-background-color: #02557a");

        //Type Cell
        TableColumn<Stock,String> productIDCol = new TableColumn<>("Model");
        productIDCol.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
        productIDCol.setMinWidth(50);


        //Amount Cell
        TableColumn<Stock,String> serialNumberCol = new TableColumn<>("Serial");
        serialNumberCol.setCellValueFactory(cellData -> cellData.getValue().serialNumberProperty());
        serialNumberCol.setMinWidth(50);

        TableColumn<Stock,String> costCol = new TableColumn<>("Cost");
        costCol.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        costCol.setMinWidth(50);



        cartTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        cartTable.getColumns().addAll(inventoryProductIDCol,productIDCol,serialNumberCol,costCol);






        ScrollPane scrollPane = new ScrollPane(cartTable);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(cartTable.getPrefWidth());
        return scrollPane;
    }
}

package Utils;

import Entities.Stock;
import Functionality.Forms.InventoryController;
import Functionality.Forms.OrdersController;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;

import java.sql.SQLException;

public class OrderTable {
    public static TableView<Stock> inventoryTable;

    public static Parent orderTable() throws SQLException {

        inventoryTable = new TableView<>();
        inventoryTable.setBorder(Border.EMPTY);
        inventoryTable.setEditable(false);
        inventoryTable.setItems(InventoryController.getInventoryList());
        inventoryTable.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER) {
                if (inventoryTable.getSelectionModel().getSelectedItem() != null) {
                    OrdersController.addOrderItem(inventoryTable.getSelectionModel().getSelectedItem());
                }
            }
        });
        TableColumn<Stock,String> makeCol = new TableColumn<>("Make");
        makeCol.setCellValueFactory(cellData -> cellData.getValue().getMakeProperty());
        makeCol.setMinWidth(50);

        TableColumn<Stock,String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
        modelCol.setMinWidth(50);

        TableColumn<Stock,String> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(cellData -> cellData.getValue().getYearProperty());
        yearCol.setMinWidth(50);

        TableColumn<Stock,String> categoryCol = new TableColumn<>("Cat");
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().productCategoryProperty());
        categoryCol.setMinWidth(0);

        TableColumn<Stock,String> serialNumberCol = new TableColumn<>("Serial");
        serialNumberCol.setCellValueFactory(cellData -> cellData.getValue().serialNumberProperty());
        serialNumberCol.setMinWidth(50);

        TableColumn<Stock,String> costCol = new TableColumn<>("Cost");
        costCol.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        costCol.setMinWidth(50);

        TableColumn<Stock,String> conditionCol = new TableColumn<>("");
        conditionCol.setCellValueFactory(cellData -> cellData.getValue().conditionProperty());
        conditionCol.setMaxWidth(40);

        inventoryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inventoryTable.getColumns().addAll(makeCol,modelCol,yearCol,categoryCol,serialNumberCol,costCol,conditionCol);



        ScrollPane scrollPane = new ScrollPane(inventoryTable);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(inventoryTable.getPrefWidth());
        return scrollPane;
    }
}

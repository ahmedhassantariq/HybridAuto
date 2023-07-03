package Utils;

import Entities.Bill;
import Functionality.Forms.BillsController;
import Functionality.Forms.InventoryController;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;

import java.sql.SQLException;

public class BillTable {
    public static TableView<Bill> billTable;

    public static Parent billTable() throws SQLException {

        billTable = new TableView<>();
        billTable.setBorder(Border.EMPTY);
        billTable.setEditable(false);
        billTable.setItems(BillsController.getBills());

        TableColumn<Bill,String> billTypeCol = new TableColumn<>("Bill");
        billTypeCol.setCellValueFactory(cellData -> cellData.getValue().billTypeProperty());
        billTypeCol.setMinWidth(50);

        TableColumn<Bill,String> billAmount = new TableColumn<>("Amount");
        billAmount.setCellValueFactory(cellData -> cellData.getValue().billAmountProperty());
        billAmount.setMinWidth(50);

        TableColumn<Bill,String> dateTimeCol = new TableColumn<>("DateTime");
        dateTimeCol.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());
        dateTimeCol.setMinWidth(50);


        billTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        billTable.getColumns().addAll(billTypeCol,billAmount,dateTimeCol);


        ScrollPane scrollPane = new ScrollPane(billTable);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(billTable.getPrefWidth());
        return scrollPane;
}
}

package Utils;

import Entities.Customer;
import Entities.Order;
import Entities.Services;
import Entities.Stock;
import Functionality.Forms.InventoryController;
import Functionality.Forms.ServicesController;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;

import java.sql.SQLException;

public class ServiceTable {
    public static javafx.scene.control.TableView<Services> serviceTable;

    public static Parent serviceTable() throws SQLException {

        serviceTable = new TableView<>();
        serviceTable.setBorder(Border.EMPTY);
        serviceTable.setEditable(false);
        serviceTable.setItems(ServicesController.servicesList);
        TableColumn<Services, String> orderIDCol = new TableColumn<>("Order-ID");
        orderIDCol.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        orderIDCol.setMinWidth(50);

        TableColumn<Services,String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        customerNameCol.setMinWidth(200);

        TableColumn<Services,String> customerContactCol = new TableColumn<>("Contact");
        customerContactCol.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
        customerContactCol.setMinWidth(150);

        TableColumn<Services,String> dateCol = new TableColumn<>("DateTime");
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        dateCol.setMinWidth(150);


        serviceTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        serviceTable.getColumns().addAll(orderIDCol,customerNameCol,customerContactCol,dateCol);






        ScrollPane scrollPane = new ScrollPane(serviceTable);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(serviceTable.getPrefWidth());
        return scrollPane;
    }
}

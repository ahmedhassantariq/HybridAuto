package Utils;

import Entities.Device;
import Entities.Model;
import Entities.Person;
import Entities.Product;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class TableViews {

    private MFXPaginatedTableView<Product> table;


    public TableViews(){
        table = new MFXPaginatedTableView<>();
        setupTable();
    }
    public MFXPaginatedTableView<Product> getTable(){
        return this.table;
    }


    private void setupTable() {
        MFXTableColumn<Product> idColumn = new MFXTableColumn<>("ID", true);
        MFXTableColumn<Product> makeColumn = new MFXTableColumn<>("Make", true);
        MFXTableColumn<Product> modelColumn = new MFXTableColumn<>("Model", true);
        MFXTableColumn<Product> yearColumn = new MFXTableColumn<>("Year", true);
        MFXTableColumn<Product> productIDColumn = new MFXTableColumn<>("Product", true);
        MFXTableColumn<Product> conditionColumn = new MFXTableColumn<>("Condition", true);
        MFXTableColumn<Product> serialColumn = new MFXTableColumn<>("Serial", true);
        MFXTableColumn<Product> costColumn = new MFXTableColumn<>("Cost", true);
        MFXTableColumn<Product> descriptionColumn = new MFXTableColumn<>("Description", true);


        idColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getInventoryProductID));
        makeColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getMake));
        modelColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getModel));
        yearColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getYear));
        productIDColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getPID));
        conditionColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getCondition));
        serialColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getSerial));
        costColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getCost));
        descriptionColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getDescription));


        costColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getCost) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        costColumn.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(
                idColumn,
                makeColumn, modelColumn, yearColumn,
                productIDColumn,conditionColumn,
                serialColumn, costColumn, descriptionColumn);

        table.getFilters().addAll(
                new StringFilter<>("Make", Product::getMake),
                new StringFilter<>("Model", Product::getModel),
                new IntegerFilter<>("Year",Product::getYear),
                new IntegerFilter<>("Cost",Product::getCost),
                new StringFilter<>("Product",Product::getProductID)
        );
        table.setItems(Model.products);
    }


}

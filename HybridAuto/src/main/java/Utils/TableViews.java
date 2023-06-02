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
        table.setFooterVisible(false);

        setupTable();
    }
    public MFXPaginatedTableView<Product> getTable(){
        return this.table;
    }


    private void setupTable() {
        MFXTableColumn<Product> nameColumn = new MFXTableColumn<>("ProductID", true);
        MFXTableColumn<Product> surnameColumn = new MFXTableColumn<>("Cost", true);
        MFXTableColumn<Product> ageColumn = new MFXTableColumn<>("Description", true);

        nameColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getProductID));
        surnameColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getCondition));
        ageColumn.setRowCellFactory(Product -> new MFXTableRowCell<>(Product::getCarID) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        ageColumn.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(nameColumn, surnameColumn, ageColumn);
        table.getFilters().addAll( // TODO: 6/2/2023 update type of filter to match with type of product attribute
                new StringFilter<>("Name", Product::getProductID),
                new StringFilter<>("Surname", Product::getCondition)
        );
        table.setItems(Model.products);
    }


}

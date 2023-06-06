package Screens;

import Entities.Stock;
import Functionality.Forms.OrdersController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import Utils.CartTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CheckOutForm {

    public static VBox checkOutForm() {

        Label subtotalLabel = Labels.checkOutLabel("Subtotal: 0");
        Label discountLabel = Labels.checkOutLabel("Discount: 0");
        Label qtyLabel = Labels.checkOutLabel("Item-Qty: 0");
        Label totalLabel = Labels.checkOutLabel("Total: 0");

        OrdersController.orderList.addListener((ListChangeListener<? super Stock>) e->{
            double subtotal = 0;
            double discount = 10;
            if(OrdersController.orderList.size()==0){
                subtotalLabel.setText("Subtotal: 0");
                discountLabel.setText("Discount: 0");
                qtyLabel.setText("Item-Qty: 0");
                totalLabel.setText("Total: 0");
            }else {
                for (int i = 0; i < OrdersController.orderList.size(); i++) {
                    subtotal += Double.parseDouble(OrdersController.orderList.get(i).getCost());
                    subtotalLabel.setText("Subtotal: " + subtotal);
                }
                qtyLabel.setText("Item-Qty: "+OrdersController.orderList.size());

                discountLabel.setText("Discount: "+(discount/subtotal)*100);
                totalLabel.setText("Total: "+subtotal*(discount/subtotal));
            }
        });


        VBox totalsBox = new VBox(subtotalLabel,qtyLabel,discountLabel,totalLabel);
        totalsBox.setMinHeight(40);





        VBox cutomerBox = new VBox(Labels.titleLabel("Check Out"),totalsBox);
        cutomerBox.setSpacing(10);
        cutomerBox.setAlignment(Pos.TOP_CENTER);
        cutomerBox.setPrefSize(300, 400);
        cutomerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
//        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));

        cutomerBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return cutomerBox;
    }
}

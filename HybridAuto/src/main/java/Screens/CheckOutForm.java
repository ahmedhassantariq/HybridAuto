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
    private static Label subtotalLabel = Labels.checkOutLabel("Subtotal: 0");
    private static Label discountLabel = Labels.checkOutLabel("Discount: 0");
    private static Label qtyLabel = Labels.checkOutLabel("Item-Qty: 0");
    private static Label totalLabel = Labels.checkOutLabel("Total: 0");
    private static MFXTextField discountField = Fields.textField("Discount %",150,40);
    private static MFXTextField discountAmountField = Fields.textField("Discount Amount",150,40);

    public static VBox checkOutForm() {

        HBox discountFields = new HBox(discountField,discountAmountField);
        discountFields.setAlignment(Pos.CENTER);
        discountFields.setPadding(new Insets(10));
        discountFields.setSpacing(10);

        MFXButton checkoutButton = Buttons.FunctionButton("CheckOut", 100, 40);
        MFXButton receiptButton = Buttons.FunctionButton_Border("Print Receipt", 100, 40);
        HBox actionButtons = new HBox(checkoutButton,receiptButton);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setPadding(new Insets(10));
        actionButtons.setSpacing(10);

        OrdersController.orderList.addListener((ListChangeListener<? super Stock>) e->{
            updateCart();
        });
        discountField.textProperty().addListener(e->{
            updateCart();
        });
        discountAmountField.textProperty().addListener(e->{
            updateCart();
        });


        VBox totalsBox = new VBox(discountFields, subtotalLabel,qtyLabel,discountLabel,totalLabel,actionButtons);
        totalsBox.setMinHeight(40);





        VBox cutomerBox = new VBox(Labels.titleLabel("Check Out"),totalsBox);
        cutomerBox.setSpacing(10);
        cutomerBox.setAlignment(Pos.TOP_CENTER);
        cutomerBox.setPrefSize(300, 400);
        cutomerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));

        cutomerBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return cutomerBox;
    }

    private static void updateCart(){
        double subtotal = 0;
        double discount = 0;
        double discountAmount = 0;
        double totalDiscount = 0;
        double total = 0;
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
            if(!discountField.getText().isEmpty()){
                discount = Double.parseDouble(discountField.getText());
            }
            if(!discountAmountField.getText().isEmpty()){
                discountAmount = Double.parseDouble(discountAmountField.getText());
            }
            total = subtotal-(subtotal*(discount/100));
            total -=discountAmount;
            totalDiscount = 100-(total/subtotal)*100;
            discountLabel.setText("Discount: "+Formatter.decimalFormat().format(totalDiscount)+"%");
            totalLabel.setText("Total: "+total);
        }
    }
}

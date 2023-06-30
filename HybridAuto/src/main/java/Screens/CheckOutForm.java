package Screens;

import Entities.Stock;
import Functionality.Forms.OrdersController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import Utils.PDFDocument;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class CheckOutForm {
    public static Label orderIDLabel = Labels.checkOutLabel("Order-ID: "+OrdersController.newOrderID());
    private static Label subtotalLabel = Labels.checkOutLabel("Subtotal: 0");
    private static Label discountLabel = Labels.checkOutLabel("Discount: 0");
    private static Label qtyLabel = Labels.checkOutLabel("Item-Qty: 0");
    private static Label totalLabel = Labels.checkOutLabel("Total: 0");
    public static MFXTextField discountField = Fields.textField("Discount %",100,40);
    public static MFXTextField discountAmountField = Fields.textField("Discount Amount",100,40);
    public static MFXButton checkoutButton = Buttons.FunctionButton("CheckOut", 100, 40);
    private static double subtotal = 0;
    private static double discount = 0;
    private static double discountAmount = 0;
    public static double totalDiscount = 0;
    private static double total = 0;
    public static double totalAmount;
    public static MFXButton receiptButton = Buttons.FunctionButton_Border("Print Receipt", 100, 40);

    public static VBox checkOutForm() {

        HBox discountFields = new HBox(discountField,discountAmountField);
        discountFields.setAlignment(Pos.CENTER);
        discountFields.setPadding(new Insets(10,0,0,10));
        discountFields.setSpacing(10);
        discountField.delegateSetTextFormatter(Formatter.digitFormatter());
        discountField.setTextLimit(3);
        discountAmountField.delegateSetTextFormatter(Formatter.digitFormatter());

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
        receiptButton.setOnAction(e->{
            PDFDocument.show();
        });


        VBox labelsBox = new VBox(orderIDLabel,subtotalLabel,qtyLabel,discountLabel,totalLabel);
        labelsBox.setPadding(new Insets(0,0,0,10));
        labelsBox.setAlignment(Pos.CENTER_LEFT);
        labelsBox.setBorder(Border.stroke(Color.web("#dcdcdc")));

        VBox cartBox = new VBox(Labels.titleLabel("Check Out"),discountFields,labelsBox,actionButtons);
        cartBox.setSpacing(10);
        cartBox.setAlignment(Pos.TOP_CENTER);
        cartBox.setPrefSize(300, 350);
        cartBox.setMaxSize(300,400);
        cartBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));

        cartBox.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
        return cartBox;
    }

    private static void updateCart(){
        subtotal = 0;
        discount = 0;
        discountAmount = 0;
        totalDiscount = 0;
        total = 0;
        if(OrdersController.orderList.size()==0){
            subtotalLabel.setText("Subtotal: 0");
            discountLabel.setText("Discount: 0");
            qtyLabel.setText("Item-Qty: 0");
            totalLabel.setText("Total: 0");
        }else {
            for (int i = 0; i < OrdersController.orderList.size(); i++) {
                subtotal += Double.parseDouble(OrdersController.orderList.get(i).getCost());
                subtotalLabel.setText("Subtotal: " + Formatter.doublePrefix(subtotal));
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
            totalLabel.setText("Total: "+Formatter.doublePrefix(total));
            totalAmount = total;
        }
    }
}

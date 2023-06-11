package Screens;

import Functionality.Forms.DashboardController;
import Styles.Cards;
import Styles.Labels;
import Utils.Formatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DashboardForm {

    public static Parent dashboardForm(){
        Parent p1 = Cards.card("Total Sales", Formatter.doublePrefix(DashboardController.totalSales()),"Orders");
        Parent p2 = Cards.card("Products",Formatter.decimalPrefix(DashboardController.totalStock()),"Stock");
        Parent p3 = Cards.card("Total Orders",Formatter.decimalPrefix(DashboardController.totalOrders()),"Generated");
        Parent p4 = Cards.card("Total Customers",Formatter.decimalPrefix(DashboardController.totalCustomers()),"Active");

        Parent p5 = Cards.card("Total Bills", Formatter.doublePrefix(DashboardController.totalBillsAmount()),"Amount");
        Parent p6 = Cards.card("Inventory Assets",Formatter.doublePrefix(DashboardController.totalInventoryCost()),"Product");
        Parent p7 = Cards.card("Sales-Bills",Formatter.doublePrefix(DashboardController.profitLoss()),"Overall");
        Parent p8 = Cards.card("Products",Formatter.decimalPrefix(DashboardController.totalItemsSold()),"Sold");


        HBox cardBox1 = new HBox(p1,p2,p3,p4);
        cardBox1.setAlignment(Pos.CENTER);
        cardBox1.setPadding(new Insets(10));
        cardBox1.setSpacing(10);
        cardBox1.setBorder(Border.stroke(Color.web("#dcdcdc")));
        HBox cardBox2 = new HBox(p5,p6,p7,p8);
        cardBox2.setAlignment(Pos.CENTER);
        cardBox2.setPadding(new Insets(10));
        cardBox2.setSpacing(10);
        cardBox2.setBorder(Border.stroke(Color.web("#dcdcdc")));

        VBox dashboardBox = new VBox(Labels.titleLabel("Dashboard"),cardBox1,cardBox2);
        dashboardBox.setPadding(new Insets(10,0,0,0));
        dashboardBox.setSpacing(10);
        dashboardBox.setMinSize(300,400);
        dashboardBox.setBackground(new Background(new BackgroundFill(Color.web("#e8e8e8"),new CornerRadii(0,0,0,0,false),null)));

        return dashboardBox;

    }
}

package Screens;

import Styles.Buttons;
import Styles.Cards;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.base.MFXLabeled;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import io.github.palexdev.materialfx.css.themes.Themes;
import io.github.palexdev.materialfx.skins.MFXProgressSpinnerSkin;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DashboardForm {

    public static Parent dashboardForm(){
        Parent p1 = Cards.card("Total Sales","Rs. 42M","Invoice");
        Parent p2 = Cards.card("Open Sales","Rs. 102M","");
        Parent p3 = Cards.card("Open Purchase","Rs. 62M","");
        Parent p4 = Cards.card("Total Customer","1003","Active");


        HBox cardBox = new HBox(p1,p2,p3,p4);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(10));
        cardBox.setSpacing(10);
        cardBox.setBorder(Border.stroke(Color.web("#dcdcdc")));

        VBox dashboardBox = new VBox(Labels.titleLabel("Dashboard"),cardBox);
        dashboardBox.setPadding(new Insets(10,0,0,0));
        dashboardBox.setSpacing(10);
        dashboardBox.setMinSize(300,400);
        dashboardBox.setBackground(new Background(new BackgroundFill(Color.web("#e8e8e8"),new CornerRadii(0,0,0,0,false),null)));

        return dashboardBox;

    }
}

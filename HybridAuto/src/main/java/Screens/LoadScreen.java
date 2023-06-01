package Screens;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import io.github.palexdev.materialfx.css.themes.Themes;
import io.github.palexdev.materialfx.skins.MFXProgressSpinnerSkin;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoadScreen {
    private static BorderPane viewPane = new BorderPane();
    private static MFXProgressSpinner progressSpinner = new MFXProgressSpinner(25);
    private static MFXProgressSpinnerSkin spinnerSkin = new MFXProgressSpinnerSkin(progressSpinner);

    public static Parent loadForm(){
        viewPane.getStylesheets().add(Stylesheets.PROGRESS_SPINNER.loadTheme());
        progressSpinner.setProgress(10);
        viewPane.setCenter(progressSpinner);

        return viewPane;
    }
}

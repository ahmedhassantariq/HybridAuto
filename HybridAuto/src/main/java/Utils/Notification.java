package Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.Notifications;

import java.util.concurrent.atomic.AtomicBoolean;

public class Notification {
    private static Notifications notifications = Notifications.create();
    private static Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public Notification(Throwable cause){
        notifications.title("Notification");
        notifications.text(cause.getMessage());
        notifications.show();
    }
    public Notification(String cause){
        notifications.title("Notification");
        notifications.text(cause);
        notifications.show();
    }

}

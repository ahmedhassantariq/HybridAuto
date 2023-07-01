package Utils;

import org.controlsfx.control.Notifications;

public class Notification {
    private Notifications notifications = Notifications.create();


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

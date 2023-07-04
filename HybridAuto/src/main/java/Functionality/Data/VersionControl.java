package Functionality.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VersionControl implements Serializable {
    private double appVersion;
    private LocalDateTime localDateTime;

    public VersionControl(double appVersion, LocalDateTime localDateTime) {
        this.appVersion = appVersion;
        this.localDateTime = localDateTime;
    }

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
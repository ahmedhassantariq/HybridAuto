package Functionality.Data;

import java.io.Serializable;

public class VersionControl implements Serializable {
    private double appVersion;
    private boolean update;

    public VersionControl(double appVersion, boolean update) {
        this.appVersion = appVersion;
        this.update = update;
    }

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}

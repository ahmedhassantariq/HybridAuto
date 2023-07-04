package Utils;

import Functionality.Data.VersionControl;

import java.io.*;
import java.time.LocalDateTime;

public class FileManager {

    public static void writeSettings() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePaths.settingsFilePath));
            VersionControl newSettings = new VersionControl(1.0,LocalDateTime.now());
            objectOutputStream.writeObject(newSettings);
            objectOutputStream.close();
            System.out.println("Settings Written");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readSettings() {
        if(FilePaths.settingsFilePath.exists()) {
            ObjectInputStream objectInputStream;
            VersionControl currentSettings = null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(FilePaths.settingsFilePath));
                currentSettings = (VersionControl) objectInputStream.readObject();
                objectInputStream.close();
                System.out.println("Settings Read");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("EOF");
            } finally {
                if (currentSettings != null) {
                    Constants.settings = currentSettings;
                    System.out.println("Version:" + Constants.settings.getAppVersion());
                }
            }
        }else {
            writeDefaultSettings();
        }
    }
    private static void writeDefaultSettings(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePaths.settingsFilePath));
            VersionControl newSettings = new VersionControl(1.0,LocalDateTime.now());
            objectOutputStream.writeObject(newSettings);
            objectOutputStream.close();
            System.out.println("Settings Written");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package Functionality.Database.DB;

import Functionality.Data.VersionControl;
import Screens.MainScreen;
import Screens.StatusScreen;
import Utils.*;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.StorageClient;

import java.io.*;
import java.sql.SQLException;

import static Utils.FilePaths.applicationFilePath;

public class Firebase {
   private static FileInputStream serviceAccount;
   private static FirebaseOptions options;
   private static FirebaseApp fireApp;
   private static StorageClient storageClient;
   private static FirebaseAuth auth;
   private static String code = null;
    private static Long blobSize = 1L;
    private static boolean isDownloaded = true;


    static {
        try {
            serviceAccount = new FileInputStream(FilePaths.serviceAccountPath);
//            options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setStorageBucket("database-f8eac.appspot.com")
//                    .build();
            options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("hybridauto.appspot.com")
                    .build();
            fireApp = FirebaseApp.initializeApp(options);
            storageClient = StorageClient.getInstance(fireApp);
            auth = FirebaseAuth.getInstance(fireApp);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void downloadData(){
            OutputStream outputStream;
            if(Internet.isConnected()) {
                try {
                    Blob blob = storageClient.bucket().get("Version.config");
                    if (blob != null) {
                        outputStream= new FileOutputStream(FilePaths.versionFilePath);
                        blob.downloadTo(outputStream);
                        outputStream.close();
                    }
                } catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            } else {
                System.out.println("Not Connected");
            }
    }

    private static void uploadData() {
        if(Internet.isConnected()) {
            try {
                InputStream file = new FileInputStream(FilePaths.versionFilePath);
                storageClient.bucket().create("Version.config", file);
                System.out.println("VersionFile Uploaded");
            } catch (FileNotFoundException e) {
                new Notification(e);
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not Connected");
        }
    }

    public static boolean userAuth(String email){
        try {
            UserRecord userRecord = auth.getUserByEmail(email);
            code = Email.SendCode(userRecord.getEmail());
            if(!userRecord.isDisabled()&&code!=null) {
                return true;
            }else {
                return false;
            }
        } catch (FirebaseAuthException e) {
            new Notification(e);
            return false;
        }
    }
    public static void codeAuth(String input){
      if(code.equals(input)){
          try {
              Constants.setScene(MainScreen.mainScreen());
          } catch (SQLException | ClassNotFoundException e ) {
              new Notification(e);
          }
      }else {
          new Notification("Invalid Code");
      }
    }

    public static void checkUpdate() {
        if(Internet.isConnected()) {
            downloadData();
            ObjectInputStream objectInputStream;
            VersionControl currentVersion = null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(FilePaths.versionFilePath));
                currentVersion = (VersionControl) objectInputStream.readObject();
                objectInputStream.close();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("EOF");
            } finally {
                if (currentVersion != null && currentVersion.getAppVersion()>Constants.appVersion) {
                    TaskManager.checkSize();
                    TaskManager.updateVersion();
                }
            }
        }
    }

    public static void updateApplication(){
        OutputStream outputStream;
        if(Internet.isConnected()) {
            try {
                Blob blob = storageClient.bucket().get("Application.exe");
                if (blob != null) {
                    blobSize = blob.getSize();
                    StatusScreen.progressBar.setVisible(true);
                    outputStream= new FileOutputStream(applicationFilePath);
                    blob.downloadTo(outputStream);
                    outputStream.close();
                    isDownloaded = false;
                    StatusScreen.progressBar.setVisible(false);
                }
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        }
    }

    public static void checkSize(){
        while (isDownloaded){
            if (applicationFilePath.exists()) {
               StatusScreen.progressBar.setProgress(((double)applicationFilePath.length()/blobSize));
            }

        }
    }



    public static void setUpdate() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePaths.versionFilePath));
            VersionControl currentVersion = new VersionControl(1.1,true);
            objectOutputStream.writeObject(currentVersion);
            objectOutputStream.close();
            System.out.println("Updated ");
            uploadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

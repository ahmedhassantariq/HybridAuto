package Functionality.Database.DB;

import Screens.MainScreen;
import Utils.Constants;
import Utils.Email;
import Utils.FilePaths;
import Utils.Notification;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.StorageClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Firebase {
   private static FileInputStream serviceAccount;
   private static FirebaseOptions options;
   private static FirebaseApp fireApp;
   private static StorageClient storageClient;
   private static FirebaseAuth auth;
   private static String code = null;


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

//    private static void downloadData(){
//            if(Internet.isConnected()) {
//                try {
//                    Blob blob = storageClient.bucket().get("Settings.config");
//                    if (blob != null) {
//                        OutputStream outputStream= new FileOutputStream(FilePaths.settingsFilePath);
//                        System.out.println("Downloading Settings");
//                        blob.downloadTo(outputStream);
//                        outputStream.close();
//                    }
//                } catch (IOException ioException) {
//                    throw new RuntimeException(ioException);
//                }
//            } else {
//                System.out.println("Not Connected");
//            }
//    }
//
//    private static void uploadData() {
//        if(Internet.isConnected()) {
//            try {
//                InputStream file = new FileInputStream(FilePaths.settingsFilePath);
//                storageClient.bucket().create("Settings.config", file);
//                System.out.println("Settings Uploaded");
//            } catch (FileNotFoundException e) {
//                new Notification(e);
//                throw new RuntimeException(e);
//            }
//        } else {
//            System.out.println("Not Connected");
//        }
//    }

    public static boolean userAuth(String email){
        try {
            UserRecord userRecord = auth.getUserByEmail(email);

            code = Email.SendCode(userRecord.getEmail());
            if(userRecord!=null&&!userRecord.isDisabled()&&code!=null) {
                Constants.logInUsername = userRecord.getDisplayName();
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
        if(code==null){
            new Notification("Code Error");
            return;
        }
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

}

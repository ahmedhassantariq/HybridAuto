package Functionality.Database.DB;

import Screens.MainScreen;
import Screens.StatusScreen;
import Utils.Constants;
import Utils.Email;
import Utils.Internet;
import Utils.Notification;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserImportHash;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.StorageClient;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;

public class Firebase {
   private static FileInputStream serviceAccount;
   private static FirebaseOptions options;
   private static FirebaseApp fireApp;
   private static StorageClient storageClient;
   private static FirebaseAuth auth;
   private static String code = null;

   private static File usersFilePath = new File("C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Functionality\\Database\\DB\\Config.dat");


    static {
        try {
            serviceAccount = new FileInputStream("C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Functionality\\Database\\DB\\serviceAccountKey.json");
            options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://database-f8eac-default-rtdb.firebaseio.com/")
                    .setStorageBucket("database-f8eac.appspot.com")
                    .build();
            fireApp = FirebaseApp.initializeApp(options);
            storageClient = StorageClient.getInstance(fireApp);
            auth = FirebaseAuth.getInstance(fireApp);

        } catch (FileNotFoundException e) {
            new Notification(e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }


    }

    public static void downloadData(boolean now){
        if(!usersFilePath.exists()||now) {
            OutputStream outputStream;

            if(Internet.isConnected()) {
                try {
                     outputStream= new FileOutputStream(usersFilePath);
                    Blob blob = storageClient.bucket().get("Config");
                    if (blob != null) {
                        outputStream.write(blob.getContent());
                    }
                    outputStream.close();
                } catch (IOException ioException) {
                    new Notification(ioException);
                }
            } else {
                System.out.println("Not Connected");
            }
            System.out.println("Downloaded Data");
        } else {
            System.out.println("Data Already in");
        }


    }

    public static void uploadData() {
        if(Internet.isConnected()) {
            try {
                InputStream file = new FileInputStream(usersFilePath);
                storageClient.bucket().create("Config", file);
                System.out.println("Data Uploaded");
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
            throw new RuntimeException(e);
        }
    }
    public static void codeAuth(String input){
      if(code.equals(input)){
          try {
              Constants.setScene(MainScreen.mainScreen());
          } catch (SQLException | ClassNotFoundException e ) {
              new Notification(e);
              throw new RuntimeException(e);
          }
      }else {
          new Notification("Invalid Code");
      }
    }
}

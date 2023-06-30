package Functionality.Database.DB;

import Utils.Internet;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.UserAuthorizer;
import com.google.auth.oauth2.UserCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;
import com.google.firebase.auth.internal.DownloadAccountResponse;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.remoteconfig.internal.TemplateResponse;
import com.google.type.PhoneNumber;

import java.io.*;

public class Firebase {
   private static FileInputStream serviceAccount;
   private static FirebaseOptions options;
   private static FirebaseApp fireApp;
   private static StorageClient storageClient;

   private static File usersFilePath = new File("C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Functionality\\Database\\DB\\Config.dat");


    static {
        try {
            serviceAccount = new FileInputStream("C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Functionality\\Database\\DB\\serviceAccountKey.json");
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://database-f8eac-default-rtdb.firebaseio.com/")
                    .setStorageBucket("database-f8eac.appspot.com")
                    .build();
            fireApp = FirebaseApp.initializeApp(options);
            storageClient = StorageClient.getInstance(fireApp);

            FirebaseAuth auth = FirebaseAuth.getInstance(fireApp);
//            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                    .setEmail("ahmedhalksmflkmslkmassantariq00@gmail.com")
//                    .setPassword("AhmedHassan")
//                    .setPhoneNumber("+923364042535")
//                    .setEmailVerified(true)
//                    .setDisplayName("Mr.AhmedHassan")
//                    .setDisabled(false);
//            UserRecord userRecord = auth.createUser(request);
//            System.out.println(userRecord.getUid());


//            UserRecord userRecord1 = auth.getUserByPhoneNumber("+923364042530");
//            System.out.println(userRecord1.getDisplayName());
//            PhoneIdentifier identifier = new PhoneIdentifier("+923364042530");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not Connected");
        }
    }
}

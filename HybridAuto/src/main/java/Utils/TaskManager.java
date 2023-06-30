package Utils;

import Executive.Main;
import Functionality.Database.DB.Firebase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskManager {
    private static ExecutorService service = Executors.newFixedThreadPool(3);
    private static boolean downloadBool = false;

    public static void startApplication(String [] args){
        service.execute(()->{
            Main.main(args);
        });
    }

    public static void uploadData(){
        service.execute(new Runnable() {
            @Override
            public void run() {
                Firebase.uploadData();
            }
        });
    }

    public static void DownloadData(){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    Firebase.downloadData(true);
                }
            });
    }

}

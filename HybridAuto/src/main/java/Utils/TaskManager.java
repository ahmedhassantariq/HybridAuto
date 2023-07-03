package Utils;

import Executive.Main;
import Functionality.Database.DB.Firebase;
import Screens.UpdateScreen;
import javafx.application.Platform;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TaskManager {
    private static ExecutorService service = Executors.newFixedThreadPool(4);
    private static FutureTask<String> futureCheckUpdate = new FutureTask<>(()->{Firebase.checkUpdate();return null;});
    private static FutureTask<String> futureSize = new FutureTask<>(()->{Firebase.checkSize();return null;});
    private static FutureTask<String> futureUpdate = new FutureTask<>(()->{Firebase.updateApplication();return null;});

    public static void startApplication(String [] args){
        service.execute(()->{
            Main.main(args);
        });
    }

    public static void checkUpdate(){
        service.execute(futureCheckUpdate);
    }
    public static void checkSize(){
        service.execute(futureSize);
    }
    public static void updateVersion(){
        service.execute(futureUpdate);
    }




    public static void setUpdate(){
        service.execute(new Runnable() {
            @Override
            public void run() {
                Firebase.setUpdate();
            }
        });
    }



}

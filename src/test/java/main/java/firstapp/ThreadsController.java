package firstapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ThreadsController implements Runnable {

    private String url;
    private int counter;
    private Path path = Paths.get(".").toAbsolutePath().getParent();

    public ThreadsController(String url) {
        this.url = url;
    }
    @Override
    public void run() {

        try {
            File linksFile = new File(path + "/src/main/java/org/hillel_Elem/firstapp/resurses/links", Thread.currentThread().getName()+ ".html");
            try(BufferedWriter linksFileWriter = new BufferedWriter(new FileWriter(linksFile, true))){
                Connection.Response html = Jsoup.connect(url).execute();
                linksFileWriter.write(html.body());

                System.out.println(Thread.currentThread().getName() + " Started!");

                counter = TestMain.atomicInteger.incrementAndGet();
                System.out.println(counter +" number of Site are checked");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

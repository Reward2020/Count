package firstapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMain {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        String mainLink =   "http://websystique.com/";
        List<String> listLink = new ArrayList<>();

        Integer threadQuantity = 10;
        Document document = Jsoup.connect(mainLink).get();
        Elements linkTags = document.select("a[href]");

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadQuantity);

        String link;
        for (Element element: linkTags){
            link = element.attr("href");
            if (link.startsWith("http://websystique.com/")){
                listLink.add(link);
            }
        }
        for (int i = 0; i< threadQuantity; i++){
            firstapp.ThreadsController threadsController = new firstapp.ThreadsController(listLink.get(i));
            executor.execute(threadsController);
        }
        executor.shutdown();
    }
}

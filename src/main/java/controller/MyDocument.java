package controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MyDocument {

    public Document getDoc(String URL) throws IOException {
        return DOC(URL);
    }
    private Document DOC(String URL) throws IOException {
        return Jsoup.connect(URL)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .referrer("https://google.com")
                .timeout(15000)
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .header("Accept-Language", "ru-RU,ru;q=0.9")
                .get();
    }
}

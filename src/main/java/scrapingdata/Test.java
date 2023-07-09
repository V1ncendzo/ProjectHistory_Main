//package scrapingdata;
//
//import com.google.gson.Gson;
//import com.google.gson.stream.JsonReader;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import scrapingdata.entity.Character;
//import scrapingdata.entity.Festival;
//import scrapingdata.entity.Relic;
//
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Test {
//    public static void main(String[] args) throws IOException {
//        List<String> figureLinks = new ArrayList<String>();
//
//        for (int i = 0; i < 5; i++) {
//            try {
//                Document doc = Jsoup
//                        .connect(url + "?start=" + i * 5)
//                        .userAgent("Jsoup client")
//                        .timeout(20000).get();
//                Elements LinkCharater = doc.select("h2");
//
//                for (Element r : LinkCharater) {
//                    String link = r.select("a").attr("href");
//                    figureLinks.add("https://nguoikesu.com" + link);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        for(String ul : figureLinks){
//            System.out.println(ul);
//        }
//}
//}

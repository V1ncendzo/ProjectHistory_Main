package scrapingdata.scraping.figure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrapingdata.entity.Character;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapingNKS extends BaseScrapingFigure {
    public ScrapingNKS(String url) {
        super();
    }

    @Override
    public List<String> getFigureLinks(String url) {
        List<String> figureLinks = new ArrayList<String>();
        for (int i = 0; i < 291; i++) {

            Document doc = null;
            try {
                doc = Jsoup.connect(url + "?start=" + i * 5).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Elements LinkCharater = doc.select("h2");

            for (Element r : LinkCharater) {
                String link = r.select("a").attr("href");
                figureLinks.add("https://nguoikesu.com" + link);

            }
        }
        return figureLinks;
    }

    @Override
    public void getFigureData(List<String> figureLinks) {
        try (Writer writer = new FileWriter("src\\main\\java\\json\\Figure_NKS.json")) {
            writer.write('[');
            for (String link : figureLinks) {
                String name = null;
                String description = "";
                String sinh = null;
                String mat = null;
                String time =  null;
                Character nhanVat = new Character();
                Document characterInfo = null;
                try {
                    characterInfo = Jsoup.connect(link).get();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = characterInfo.select("div.page-header h2").text();
                Elements infobox = characterInfo.select("div[class = infobox]");
                if (infobox.size() > 0) {
                    Elements rows = infobox.get(0).select("tr");
                    for (Element r : rows) {
                        Elements infoKey = r.select("th");
                        Elements infoValue = r.select("td");
                        if (infoKey.size() > 0 && infoValue.size() > 0) {
                            String key = infoKey.text();
                            String value = infoValue.text();

                            if (key.equals("Sinh")) {
                                sinh = infoValue.text();
                            }
                            if (key.equals("Mất")) {
                                mat = infoValue.text();
                            }

                            description += key + ": " + value + "\n";
                        }
                        if (infoValue.size() == 0) {
                            String value = r.text();
                            description += value + "\n";
                        }
                    }
                    time = (sinh == null ? "Không rõ" : sinh) + " - " + (mat == null ? "Không rõ" : mat);
                    nhanVat.setTime(time);

                    Elements articleBody = characterInfo.select("div[class = com-content-article__body]");
                    Elements paragraphs = articleBody.select("p");
                    if (paragraphs.size() > 0) {
                        int count = 0;
                        for (Element p : paragraphs) {
                            if (!p.text().equals("")) {
                                count++;
                                if(count <= 2){
                                    description += p.text() + "\n";
                                }
                            }
                        }
                    }
                    nhanVat.setName(name);
                    nhanVat.setDescription(description);
//                    nhanVat.setAotherName(aotherName);
//                    nhanVat.setEra(era);
//                    nhanVat.setPlace(place);
                    System.out.println(nhanVat.hienthi());
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    gson.toJson(nhanVat, writer);
                    writer.write(",\n");


                } else {
                    Elements articleBody = characterInfo.select("div[class = com-content-article__body]");
                    Elements describe = articleBody.select("p");
                    if(describe.size()>0) {
                        String info = describe.get(0).text();
                        String timeRegex ="("+name+" )"+"(\\()([^)]*)(\\))";
                        Pattern p = Pattern.compile(timeRegex);
                        Matcher matcher = p.matcher(info);
                        boolean matchFound = matcher.find();
                        if(matchFound) {
                            String nameTime = matcher.group();
                            String Regex = "\\(.*\\)";
                            Pattern pattern = Pattern.compile(Regex);
                            Matcher m = pattern.matcher(nameTime);
                            boolean match = m.find();
                            time = "Không rõ";
                            if(match){
                                String str = m.group();
                                time = str.substring(1, str.length()-1);
                            }
                            nhanVat.setName(name);
                            nhanVat.setTime(time);
                            Elements describe1 = articleBody.select("#toc ~ *");
                            if(describe1.size()>0) {
                                int count = 0;
                                for(Element p1 : describe1) {
                                    if(!p1.text().equals("")){
                                        count ++;
                                        if(count <= 2){
                                            description+=p1.text()+"\n";
                                        }
                                    }
                                }
                            }else {
                                description = "";
                            }
                            nhanVat.setDescription(description);

                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            gson.toJson(nhanVat, writer);
                            System.out.println(nhanVat.hienthi());
                            writer.write(",\n");

                        }
                    }
                }
            }
            writer.write(']');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String url = "https://nguoikesu.com/nhan-vat";
        ScrapingNKS test = new ScrapingNKS(url);

        List<String> figureLinks = test.getFigureLinks(url);          // Call the getAllUrl method
		System.out.println(figureLinks.size());    //1452
        test.getFigureData(figureLinks);
    }
}
package scrapingdata.entity;



import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Festival extends BaseEntity {
    private String place;
    private String firstTime;
    private String character;

    public Festival() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
    @Override
    public String hienthi() {
        return "Tên: " + this.getName() + "\n" + "Thời gian: " + this.getTime() + "\n" + "Địa điểm: " + this.getPlace() + "\n" + "Lần đầu tổ chức: " + this.getFirstTime() + "\n" + "Nhân vật: " + this.getCharacter()
                + "\n" + "Chi Tiết: " + this.getDescription();
    }

    public List<Festival> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Festival_Wiki.json"));
        List<Festival> festivalList = Arrays.asList(gson.fromJson(reader, Festival[].class)); //dks is arraylist of festival
        reader.close();
        System.out.println(gson.toJson(festivalList));

        return festivalList;
    }

}

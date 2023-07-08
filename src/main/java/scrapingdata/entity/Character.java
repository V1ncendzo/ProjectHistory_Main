package scrapingdata.entity;



import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Character extends BaseEntity {
    private String otherName;
    private String place;
    public Character(){
        super();
    }
    private List<Character> relatedToCharacter;

    public Character(String otherName) {
        this.otherName = otherName;
    }

    public Character(String name, String time, String depcription, String aotherName) {
        super();
        this.otherName = null;
        this.place = null;
//        this.era = null;
    }
    public String getotherName() {
        return otherName;
    }
//    public String getPlace() {
//        return place;
//    }
//    public List<String> getEra() {
//        return era;
//    }

//    public Character (String name, String time, String description,String otherName,String place,List<String> era) {
//        super(name,time,description);
//        this.otherName = otherName;
//        this.place = place;
//        this.era = era;
//    }
    public void setotherName(String otherName) {
        if(otherName!=null)this.otherName = otherName;
        else {
            this.otherName = "Không có";
        }
    }
//    public void setPlace(String place) {
//        if(place != null) this.place = place;
//        else this.place = "Không rõ";
//    }
//    public void setEra(List<String> era) {
//        this.era = era;
//    }
    @Override
    public String hienthi() {
        return "Tên: " + this.getName() + "\n" + "Năm sinh - năm mất: " + this.getTime() + "\n" + /*"Tên khác: " + this.getotherName() +*/ "\n" + /*"Quê quán: " + this.getPlace() +*/ "\n" + /*"Thời: " + /*this.getEra().toString()
                +*/ "\n" + "Chi tiết: " + this.getDescription();
    }

    @Override
    public List<Character> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Figure.json"));
        List<Character> characterList = Arrays.asList(gson.fromJson(reader, Character[].class));
        reader.close();
        return characterList;
    }

    public List<Character> getRelatedToCharacter() {
        return relatedToCharacter;
    }
}

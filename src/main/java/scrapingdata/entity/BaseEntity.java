package scrapingdata.entity;

import java.io.IOException;
import java.util.List;

public abstract class BaseEntity {
    private int id;
    private static int entityID = 0;
    private String name;
    private String time;
    private String description;
    private String moreInfo;
    public BaseEntity(){
        entityID++;
        this.id = entityID;
    }
    public BaseEntity(String name, String time, String description) {
        this.name = name;
        this.time = time;
        this.description = description;
        entityID++;
        this.id = entityID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null) this.name = name;
        else this.name = "Không rõ";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if(time!=null) this.time = time;
        else this.time = "Không rõ";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description!=null) this.description = description;
        else this.description = "";
    }
    public abstract String hienthi();


    public boolean isRelated(String name){
        if(description == null && moreInfo == null)
            return false;

        if (description == null)
            return moreInfo.contains(name);

        if (moreInfo == null)
            return description.contains(name);

        return description.contains(name) || moreInfo.contains(name);
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }


}

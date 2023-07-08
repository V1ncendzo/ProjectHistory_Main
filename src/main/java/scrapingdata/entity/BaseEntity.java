package scrapingdata.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntity {
    private String name;
    private String time;
    private String description;
    private List<BaseEntity> relatedEntity;     // Danh sach lien ket
    private String moreInfo;
    public BaseEntity(){

    }
    public BaseEntity(String name, String time, String description) {
        this.name = name;
        this.time = time;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null) this.name = name;
        else this.name = "Không rõ";
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
    public abstract List loadDataJson() throws IOException;
    public void addRelatedEntity(BaseEntity relatedEntity){
        this.relatedEntity.add(relatedEntity);
    }
    public List<BaseEntity> getRelatedEntity(){
        return relatedEntity;
    }

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

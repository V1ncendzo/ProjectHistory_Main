package scrapingdata.entity;

import java.util.List;

public class Event extends BaseEntity {

    public Event() {

    }
    private String location;
    public List<Integer> relate;
    public Event(String name, String time, String description, String location) {
        super(name, time, description);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String hienthi() {
        return  "Tên: " + this.getName() + "\n" + "Thời gian: " + this.getTime() + "\n" + "Triều đại: " + this.location + "\n" + "Chi tiết: " + this.getDescription();
    }

    public List<Integer> getRelate() {
        return relate;
    }

    public Event setRelate(List<Integer> relate) {
        this.relate = relate;
        return null;
    }
}

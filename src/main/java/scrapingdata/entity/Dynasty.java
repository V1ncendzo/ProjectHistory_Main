
package scrapingdata.entity;



public class Dynasty extends BaseEntity {

    private String kingdom;
    private String capital;
    public String relate;

    public Dynasty(String name, String kingdom, String capital, String description) {
        super(name,description);
        this.kingdom = kingdom;
        this.capital = capital;
    }
    public Dynasty(){
    }

    public String getKingdom() {
        if(this.kingdom == null){
            return "Không rõ";
        }
    return kingdom;
    }
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
    public String getCapital() {
        if(this.capital == null){
            return "Không rõ";
        }
        return capital;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String hienthi() {
        return "Tên: " + this.getName() + "\n" + "Thời gian: " + this.getTime() + "\n" + "Tên nước: " + this.getKingdom() + "\n" + "Thủ đô: " + this.getCapital() + "\n" + "các đời vua: " + /*this.getKing().toString()*/
                 "\n" +  "Chi tiết: " + this.getDescription();
    }

    public String getRelate() {
        return relate;
    }

    public void setRelate(String relate) {
        this.relate = relate;
    }
}

package scrapingdata.entity;

public class Relic extends BaseEntity{
    private String place;
    private String province;

    private String certifacte;

    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCertifacte() {
        return certifacte;
    }

    public void setCertifacte(String certifacte) {
        this.certifacte = certifacte;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Override
    public String hienthi() {
        return "Tên: " + this.getName() + "\n" + "Tỉnh:" + this.getProvince() + "\n" + "Chứng nhận: " + this.getCertifacte() + "\n" + "Chi tiết: " + this.getDescription();
    }
}

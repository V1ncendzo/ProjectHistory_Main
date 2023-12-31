package scrapingdata.entity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Relic extends BaseEntity{
    private List<Integer> relate;

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

    public List<Integer> getRelate() {
        return relate;
    }

    public void setRelate(List<Integer> relate) {
        this.relate = relate;
    }
}

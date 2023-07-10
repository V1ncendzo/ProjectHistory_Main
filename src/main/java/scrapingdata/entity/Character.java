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
    private String sinh;
    private String mat;
    private String ngheNghiep;


    public Character(){
        super();
    }
    private List<Character> relatedToCharacter;



    public Character(String name, String time, String depcription, String aotherName, String sinh, String mat,String ngheNghiep) {
        super(name,time,depcription);
        this.sinh = sinh;
        this.mat = mat;
        this.otherName = aotherName;
        this.ngheNghiep = ngheNghiep;

    }
    public String getotherName() {
        return otherName;
    }
    public void setotherName(String otherName) {
        if(otherName!=null)this.otherName = otherName;
        else {
            this.otherName = "Không có";
        }
    }
    @Override
    public String hienthi() {
        return "Tên: " + this.getName() + "\n" + "Năm sinh - năm mất: " + this.getTime() + "\n" + "Tên khác: " + this.getotherName() + "\n" + /*"Quê quán: " + this.getPlace() +*/ "\n" + /*"Thời: " + /*this.getEra().toString()
                +*/ "\n" + "Chi tiết: " + this.getDescription();
    }

    public String getSinh() {
        return sinh;
    }

    public void setSinh(String sinh) {
        this.sinh = sinh;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getNgheNghiep() {
        if(this.ngheNghiep == null){
            return "Trong phần miêu tả";
        }
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }


}

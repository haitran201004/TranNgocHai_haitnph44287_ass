package com.example.tranngochai_haitnph44287_ass.DTO;

public class SpinDTO {
    int id;
    float thuong;
    String ngaynhan;

    public SpinDTO(String ngaynhan, int id, float thuong) {
        this.ngaynhan = ngaynhan;
        this.id = id;
        this.thuong = thuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getThuong() {
        return thuong;
    }

    public void setThuong(float giatri) {
        this.thuong = giatri;
    }

    public String getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(String ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public SpinDTO() {
    }
}

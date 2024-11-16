package com.example.tranngochai_haitnph44287_ass.DTO;

public class CongViecDTO {
    private int id;
    private String name;
    private String content;
    private int status;
    private String start_date;
    private String end_date;

    public CongViecDTO() {
    }

    public CongViecDTO(String name, String content, int status, String start_date, String end_date) {
        this.name = name;
        this.content = content;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}

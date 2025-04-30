package com.nichi.frontendnikkie225;

public class dividenddto extends divisordto {

    private Integer Date;
    private String Index;
    private Integer DivDate;
    private Double Dividend;
    private String UpdateSource;
    private String UpdateTime;

    public dividenddto() {
    }

    public dividenddto(Integer Date, String Index, Integer DivDate, Double Dividend, String UpdateSource, String UpdateTime) {
        this.Date = Date;
        this.Index = Index;
        this.DivDate = DivDate;
        this.Dividend = Dividend;
        this.UpdateSource = UpdateSource;
        this.UpdateTime = UpdateTime;
    }



    public Integer getDate() {
        return Date;
    }

    public void setDate(Integer date) {
        Date = date;
    }

    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public Integer getDivDate() {
        return DivDate;
    }

    public Double getDividend() {
        return Dividend;
    }

    public void setDividend(Double dividend) {
        Dividend = dividend;
    }

    public void setDivDate(Integer divDate) {
        DivDate = divDate;
    }

    public String getUpdateSource() {
        return UpdateSource;
    }

    public void setUpdateSource(String updateSource) {
        UpdateSource = updateSource;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }
}

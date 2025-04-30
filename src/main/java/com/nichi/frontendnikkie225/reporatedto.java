package com.nichi.frontendnikkie225;

public class reporatedto {

    private Integer Date;
    private String Index;
    private Integer Term;
    private Double Bid;
    private Double Offer;
    private String UpdateSource;
    private String UpdateTime;

    public reporatedto() {
    }

    public reporatedto(Integer Date, String Index, Integer Term, Double Bid,Double Offer, String UpdateSource, String UpdateTime) {
        this.Date = Date;
        this.Index = Index;
        this.Term = Term;
        this.Bid = Bid;
        this.Offer = Offer;
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

    public Integer getTerm() {
        return Term;
    }

    public void setTerm(Integer term) {
        Term = term;
    }

    public Double getBid() {
        return Bid;
    }

    public void setBid(Double bid) {
        Bid = bid;
    }

    public Double getOffer() {
        return Offer;
    }

    public void setOffer(Double offer) {
        Offer = offer;
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

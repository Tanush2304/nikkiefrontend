package com.nichi.frontendnikkie225;

public class fundingratedto {

    private String Date;
    private String Index;
    private String Term;
    private String Bid;
    private String Offer;
    private String UpdateSource;
    private String UpdateTime;

    public fundingratedto() {
    }

    public fundingratedto(String Date, String Index, String Term, String Bid, String Offer, String UpdateSource, String UpdateTime) {
        this.Date = Date;
        this.Index = Index;
        this.Term = Term;
        this.Bid = Bid;
        this.Offer = Offer;
        this.UpdateSource = UpdateSource;
        this.UpdateTime = UpdateTime;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getOffer() {
        return Offer;
    }

    public void setOffer(String offer) {
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

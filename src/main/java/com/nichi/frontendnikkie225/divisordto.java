package com.nichi.frontendnikkie225;

public class divisordto {

        private String Index;
        private Integer FromDt;
        private Integer ToDt;
        private Double Divisor;
        private String UpdateSource;
        private String UpdateTime;

        public divisordto() {

        }

        public divisordto(String Index, Integer FromDt, Integer ToDt, Double Divisor, String UpdateSource, String UpdateTime) {
            this.Index = Index;
            this.FromDt = FromDt;
            this.ToDt = ToDt;
            this.Divisor = Divisor;
            this.UpdateSource = UpdateSource;
            this.UpdateTime = UpdateTime;
        }

    public divisordto(String Index, Integer FromDt, Integer ToDt, Double Divisor) {
        this.Index = Index;
        this.FromDt = FromDt;
        this.ToDt = ToDt;
        this.Divisor = Divisor;
    }



    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public Integer getFromDt() {
        return FromDt;
    }

    public void setFromDt(Integer fromDt) {
        FromDt = fromDt;
    }

    public Integer getToDt() {
        return ToDt;
    }

    public void setToDt(Integer toDt) {
        ToDt = toDt;
    }

    public Double getDivisor() {
        return Divisor;
    }

    public void setDivsior(Double divsior) {
        Divisor = divsior;
    }

    public String getUpdateSource() {
        return UpdateSource;
    }

    public void setUpdateSource(String updateSource) {
        this.UpdateSource = updateSource;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }
}


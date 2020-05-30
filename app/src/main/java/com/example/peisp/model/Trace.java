package com.example.peisp.model;

public class Trace {
    private String[] imgIds;
    /**
     * 时间
     */
    private String acceptTime;
    /**
     * 描述、备注
     */
    private String acceptStation;

    public Trace() {
    }

    public Trace(String[] imgIds, String acceptTime, String acceptStation) {
        this.imgIds = imgIds;
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
    }

    public String[] getImgIds() {
        return imgIds;
    }

    public void setImgIds(String[] imgIds) {
        this.imgIds = imgIds;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }
}
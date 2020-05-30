package com.example.peisp.model;

public class IllegalRecord {
    private Integer state;
    private Worker worker;
    private String detail;
    private String time;

    public IllegalRecord() {
    }

    public IllegalRecord(Integer state, Worker worker, String detail, String time) {
        this.state = state;
        this.worker = worker;
        this.detail = detail;
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

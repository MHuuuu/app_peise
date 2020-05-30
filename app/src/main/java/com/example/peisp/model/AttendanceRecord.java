package com.example.peisp.model;

public class AttendanceRecord {
    private Worker worker;
    private String time;
    private String state;

    public AttendanceRecord() {
    }

    public AttendanceRecord(Worker worker, String time, String state) {
        this.worker = worker;
        this.time = time;
        this.state = state;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

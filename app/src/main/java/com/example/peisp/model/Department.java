package com.example.peisp.model;

public class Department {
    private String name;
    private Integer workerNum;

    public Department() {
        this.name = "初始化";
        this.workerNum = 1;
    }

    public Department(String name, int workerNum) {
        this.name = name;
        this.workerNum = workerNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }
}

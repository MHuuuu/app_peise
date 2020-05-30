package com.example.peisp.model;

public class Worker {
    //头像暂时缺省
    private String name;
    private Department department;
    private String station;
    private String jobNumber;
    private String phoneNum;

    public Worker(String name, Department department, String station, String jobNumber, String phoneNum) {
        this.name = name;
        this.department = department;
        this.station = station;
        this.jobNumber = jobNumber;
        this.phoneNum = phoneNum;
    }

    public Worker(String name, String department, String station, String jobNumber, String phoneNum) {
        this.name = name;
        this.department=new Department();
        this.department.setName(department);
        this.station = station;
        this.jobNumber = jobNumber;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}

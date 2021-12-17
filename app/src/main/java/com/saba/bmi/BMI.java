package com.saba.bmi;

public class BMI {
    private Double weight,length;
    private User user;
    private String status;

    public BMI(Double weight, Double length, User user, String status) {
        this.weight = weight;
        this.length = length;
        this.user = user;
        this.status = status;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

package com.saba.bmi;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class BMI {
    private double weight,length;
    private User user;
    private String status;

    public BMI(Double weight, Double length, User user, String status) {
        this.weight = weight;
        this.length = length;
        this.user = user;
        this.status = status;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Double calculateBMI(double weight, double length, User user){
        int age=user.calculateAge(user.getBirthday());
        Double bmi= ((weight/Math.pow(length,2))*age*100)/100;
        return bmi;
    }

    public void calculateStatus(){
        setStatus("Normal");
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

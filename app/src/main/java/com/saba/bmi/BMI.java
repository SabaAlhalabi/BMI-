package com.saba.bmi;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class BMI implements Serializable {
    private float weight,length,bmi_value;
    private String status,date,time;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public BMI(float weight, float length, String date, String time) {
        this.weight = weight;
        this.length = length;
        this.date=date;
        this.time=time;

        calculateStatus();

    }



    //calculate BMI status
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateStatus(){
        if(getBmi_value()<18.5)
            setStatus("Underweight");
        else if(getBmi_value()>=18.5 && getBmi_value()<25.00)
            setStatus("Healthy Weight");
        else if(getBmi_value()>=25.00 && getBmi_value()<30.00)
            setStatus("overweight");
        else if(getBmi_value()>30.00)
            setStatus("Obesity");
        else setStatus("UnKnown");
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getBmi_value() {
        return bmi_value;
    }

    public void setBmi_value(float bmi_value) {
        this.bmi_value = bmi_value;
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

package com.saba.bmi;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class BMI implements Serializable {
    private float weight,length,bmi_value;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public BMI(float weight, float length, LocalDate date, LocalTime time) {
        this.weight = weight;
        this.length = length;
        this.date=date;
        this.time=time;

        calculateStatus(this);

    }



    //calculate BMI status
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateStatus(BMI bmi){
        if(bmi.getBmi_value()<18.5)
            bmi.setStatus("Underweight");
        else if(bmi.getBmi_value()>=18.5 && bmi.getBmi_value()<25.00)
            bmi.setStatus("Healthy Weight");
        else if(bmi.getBmi_value()>=25.00 && bmi.getBmi_value()<30.00)
            bmi.setStatus("overweight");
        else if(bmi.getBmi_value()>30.00)
            bmi.setStatus("Obesity");
        else bmi.setStatus("UnKnown");
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
}

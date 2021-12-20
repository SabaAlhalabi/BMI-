package com.saba.bmi;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class User implements Serializable {
    private String name,email,password,gender,birthday;
    private BMI bmi;

    public User(String name, String email, String password, String gender, String birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public BMI getBmi() {
        return bmi;
    }

    public void setBmi(BMI bmi) {
        this.bmi = bmi; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int calculateAge(String birthday){
        int day_of_birth=Integer.parseInt(birthday.substring(0,2));
        int month_of_birth=Integer.parseInt(birthday.substring(3,5));
        int year_of_birth=Integer.parseInt(birthday.substring(6,10));

        LocalDate today=LocalDate.now();
        LocalDate birth_Date=LocalDate.of(year_of_birth, month_of_birth, day_of_birth);

        int age= Period.between(birth_Date,today).getYears();
        return age;

    }
}

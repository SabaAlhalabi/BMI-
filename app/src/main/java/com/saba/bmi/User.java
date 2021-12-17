package com.saba.bmi;

public class User {
    private String name,email,password,gender,birthday;
    private BMI bmi;

    public User(String name, String email, String password, String gender, String birthday, BMI bmi) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.bmi = bmi;
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
        this.bmi = bmi;
    }

}

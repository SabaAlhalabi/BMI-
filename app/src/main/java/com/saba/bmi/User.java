package com.saba.bmi;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User implements Serializable {
    private String name,email,password,gender,birthday;
    private ArrayList<BMI> bmis;
    private int age;
    private float agePercent;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String name, String email, String password, String gender, String birthday, ArrayList<BMI> bmis) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.bmis = bmis;

        calculateAge();
        calculateAgePercent();
        calculateBmiValue(bmis.get(bmisLength()-1));
        BMIChange();
    }

    //calculate BMI value
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateBmiValue(BMI bmi){
        float bmi_value;
        bmi_value = (float) ((bmi.getWeight()/Math.pow(bmi.getLength(),2))*this.getAgePercent());
        bmi.setBmi_value(bmi_value);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateAge(){
        int day_of_birth=Integer.parseInt(birthday.substring(0,2));
        int month_of_birth=Integer.parseInt(birthday.substring(3,5));
        int year_of_birth=Integer.parseInt(birthday.substring(6,10));

        LocalDate today=LocalDate.now();
        LocalDate birth_Date=LocalDate.of(year_of_birth, month_of_birth, day_of_birth);

        int age= Period.between(birth_Date,today).getYears();
        this.setAge(age);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateAgePercent(){
        int age = this.getAge();
        if(age>=2 && age<=10)
            setAgePercent((float) 0.7);
        else if(age>10 && age<=20){
            switch (gender){
                case "female":
                    setAgePercent((float) 0.8);
                case "male":
                    setAgePercent((float) 0.9);
            }
        }
        else setAgePercent((float) 1);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public String BMIChange(){
        String changeMessage = "No change";
        if (bmisLength()>=2){
            BMI lastBMI = getBmis().get(bmisLength()-1);
            BMI beforeLastBMI = getBmis().get(bmisLength()-2);

            float change = lastBMI.getBmi_value() - beforeLastBMI.getBmi_value();

            switch (lastBMI.getStatus()){
                case "Underweight" :
                    if (change < -0.3)
                        changeMessage = "So Bad";
                    else if(change >= -0.3 && change < 0.3)
                        changeMessage = "Little Change";
                    else if(change >= 0.3 && change < 0.6)
                        changeMessage = "Still Good";
                    else if(change >= 0.6)
                        changeMessage = "Go Ahead";
                    else changeMessage="Error";
                    break;

                case "Healthy Weight" :
                    if (change < -1)
                        changeMessage = "So Bad";
                    else if( (change >= -1 && change < -0.3) || (change >= 0.3) )
                        changeMessage = "Be Careful";
                    else if(change >= -0.3 && change < 0.3)
                        changeMessage = "Little Change";
                    else changeMessage="Error";
                    break;

                case "Overweight" :
                    if ((change < -1) || (change >=0.3 && change <0.6))
                        changeMessage = "Be Careful";
                    else if(change >= -1 && change < -0.6)
                        changeMessage = "Go Ahead";
                    else if(change >= -0.6 && change < -0.3)
                        changeMessage = "Still Good";
                    else if(change >= -0.3 && change < 0.3)
                        changeMessage = "Little Change";
                    else if(change >= 0.6)
                        changeMessage = "So Bad";
                    else changeMessage="Error";
                    break;

                case "Obesity" :
                    if (change < -0.6)
                        changeMessage = "Go Ahead";
                    else if(change >= -0.6 && change < 0)
                        changeMessage = "Little Change";
                    else if(change >= 0 && change < 0.3)
                        changeMessage = "Be Careful";
                    else if(change >= 0.3)
                        changeMessage = "So Bad";
                    else changeMessage="Error";
                    break;
            }

        }return changeMessage;

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


    public ArrayList<BMI> getBmis() {
        return bmis;
    }

    public int bmisLength() {
        return bmis.size();
    }

    public void setBmis(BMI bmi_item) {
        bmis.add(bmi_item);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getAgePercent() {
        return agePercent;
    }

    public void setAgePercent(float agePercent) {
        this.agePercent = agePercent;
    }
}

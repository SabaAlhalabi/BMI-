package com.saba.bmi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;

public class Home extends AppCompatActivity {
    TextView tv_status,tv_logout,tv_name;
    RecyclerView rv_old_status;
    Button btn_addFood,btn_addRecord,btn_viewFood;
    User signed_user;
    ArrayList<BMI> bmi_record;
    OldStatusAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //inflate
        tv_status=findViewById(R.id.home_tv_status);
        tv_logout=findViewById(R.id.home_tv3_logout);
        rv_old_status=findViewById(R.id.home_rv_oldStatus);
        btn_addFood=findViewById(R.id.home_btn_addFood);
        btn_addRecord=findViewById(R.id.home_btn_addRecord);
        btn_viewFood=findViewById(R.id.home_btn_viewFood);
        tv_name=findViewById(R.id.home_tv1_name);

        //get data from complete signUp2 activity
        Intent intent= getIntent();
        signed_user= (User) intent.getSerializableExtra("signed_user");

        String name=signed_user.getName();
        BMI user_bmi=signed_user.getBmis().get(0);
        Float weight=user_bmi.getWeight();
        Float length=user_bmi.getLength();
        String status=user_bmi.getStatus();
        LocalDate localDate= user_bmi.getDate();
        LocalTime localTime= user_bmi.getTime();


        //welcome message
        tv_name.setText("Hi, "+name);

        //status Message
        String changeMessage = signed_user.BMIChange();
        tv_status.setText(status+"("+ changeMessage + ")");




        //adding items to recycler view
        bmi_record=new ArrayList<BMI>();
        bmi_record.add(user_bmi);


        adapter=new OldStatusAdapter(bmi_record);
        RecyclerView.LayoutManager adapterManager = new LinearLayoutManager(getBaseContext());

        rv_old_status.setHasFixedSize(true);
        rv_old_status.setLayoutManager(adapterManager);
        rv_old_status.setAdapter(adapter);


        //Add Food button
        btn_addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addFoodIntent = new Intent(Home.this,AddFoodDetails.class);
                startActivity(addFoodIntent);
            }
        });

        //Add Record button
        btn_addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecordIntent = new Intent(Home.this,AddRecord.class);
                addRecordIntent.putExtra("signed_user", signed_user);
                startActivity(addRecordIntent);
            }
        });


        //View Food button
        btn_viewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewFoodIntent = new Intent(Home.this,FoodList.class);
                startActivity(viewFoodIntent);
            }
        });

        //Logout text view
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(Home.this,Login.class);
                startActivity(logoutIntent);
            }
        });

    }
}
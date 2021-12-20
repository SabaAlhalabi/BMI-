package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.temporal.Temporal;

public class Home extends AppCompatActivity {
    TextView tv_status,tv_logout,tv_name;
    RecyclerView rv_old_status;
    Button btn_addFood,btn_addRecord,btn_viewFood;
    User signed_user;

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

        Intent intent= getIntent();
        signed_user= (User) intent.getSerializableExtra("signed_user");
        tv_name.setText("Hi, "+signed_user.getName());
        tv_status.setText(signed_user.getBmi().getStatus());

    }
}
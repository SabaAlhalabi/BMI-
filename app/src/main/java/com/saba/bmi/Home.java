package com.saba.bmi;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    //OldStatusAdapter adapter;
    FirebaseAuth mAuth;

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
        mAuth=FirebaseAuth.getInstance();





        //Getting user data from firebase
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users")
                .child(mAuth.getCurrentUser().getUid());
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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
                FirebaseAuth.getInstance().signOut();
                Intent logoutIntent = new Intent(Home.this,Login.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logoutIntent);
            }
        });

    }
}
package com.saba.bmi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.content.ContentValues.TAG;

public class Home extends AppCompatActivity {
    TextView tv_status,tv_logout,tv_name;
    RecyclerView rv_old_status;
    Button btn_addFood,btn_addRecord,btn_viewFood;
    String name,password,gender,email,birthday,weight,length,date,time;
    ArrayList<BMI> bmi_record;
    OldStatusAdapter adapter;
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

        adapter = new OldStatusAdapter(bmi_record);
        rv_old_status.setAdapter(adapter);
        rv_old_status.setHasFixedSize(true);



        //Getting user data from firebase
        DatabaseReference userDatabaseRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(mAuth.getCurrentUser().getUid());
        userDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                 name = (String) snapshot.child("name").getValue();
                 password = (String) snapshot.child("password").getValue();
                 email = (String) snapshot.child("email").getValue();
                 birthday = (String) snapshot.child("birthday").getValue();
                 gender = (String) snapshot.child("gender").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadUserData:onCancelled", error.toException());
            }
        });

        //Getting Record data from firebase
        DatabaseReference recordDatabaseRef = FirebaseDatabase.getInstance().getReference("Records")
                .child(mAuth.getCurrentUser().getUid());
        recordDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot a: snapshot.getChildren()){
                    ArrayList<String> records = new ArrayList<>();
                    records.add((String) snapshot.getValue().toString());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadRecordData:onCancelled", error.toException());
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

    //View Details
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void viewUserDetails(){
        tv_name.setText(name);

        BMI bmi_record = new BMI(Float.parseFloat(weight),Float.parseFloat(length)
                ,date,time);

        tv_status.setText(bmi_record.getStatus());
    }

}
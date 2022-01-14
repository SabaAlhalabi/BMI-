package com.saba.bmi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class AddRecord extends AppCompatActivity {
    private TextView tv_increment_weight,tv_decrement_weight;
    private TextView tv_increment_length,tv_decrement_length;
    private EditText et_weight,et_length,et_date,et_time;
    private Button btn_save;
    private float weight,length;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //inflate
        tv_increment_weight = findViewById(R.id.addRecord_tv_weightIncrement);
        tv_decrement_weight = findViewById(R.id.addRecord_tv_weightDecrement);
        tv_increment_length = findViewById(R.id.addRecord_tv_lengthIncrement);
        tv_decrement_length = findViewById(R.id.addRecord_tv_lengthDecrement);
        et_weight = findViewById(R.id.addRecord_et_weight);
        et_length = findViewById(R.id.addRecord_et_length);
        btn_save = findViewById(R.id.addRecord_btn_save);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userId=mAuth.getCurrentUser().getUid();

        // + & - Buttons
        tv_increment_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_weight.getText().toString()==null)
                    weight=0;
                weight=Float.parseFloat(et_weight.getText().toString());
                weight= (float) (weight+1);
                et_weight.setText(Float.toString(weight));
            }
        });

        tv_decrement_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_weight.getText().toString()==null)
                    weight=0;
                weight=Float.parseFloat(et_weight.getText().toString());
                if(weight>0.00){
                    weight= (float) (weight-1.00);
                    et_weight.setText(Double.toString(weight));
                }
            }
        });

        tv_increment_length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_length.getText().toString()==null)
                    length=0;
                length=Float.parseFloat(et_length.getText().toString());
                length= (float) (length+1.00);
                et_length.setText(Double.toString(length));
            }
        });

        tv_decrement_length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(length>0.00){
                    if(et_length.getText().toString()==null)
                        length=0;
                    length=Float.parseFloat(et_length.getText().toString());
                    length= (float) (length-1.00);
                    et_length.setText(Double.toString(length));
                }
            }
        });



        //Save Record Button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                float weight=Float.parseFloat(et_weight.getText().toString());
                float length=Float.parseFloat(et_length.getText().toString());
                String date= et_date.getText().toString();
                String time= et_time.getText().toString();

                HashMap<String,Object> userRecords = new HashMap<>();
                userRecords.put("weight",weight);
                userRecords.put("length",length);
                userRecords.put("date",date);
                userRecords.put("time",time);

                FirebaseDatabase.getInstance().getReference("Records").child(userId)
                        .setValue(userRecords).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getBaseContext(),"Record Added",Toast.LENGTH_LONG);
                            Intent homeIntent = new Intent(getBaseContext(),Home.class);
                            startActivity(homeIntent);
                        }else
                            Toast.makeText(getBaseContext(),"Failed"+task.getException().getMessage(),Toast.LENGTH_LONG);
                    }
                });

            }
        });


    }
}
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CompleteSignup2 extends AppCompatActivity {
    private RadioGroup rg_gender;
    private TextView tv_increment_weight,tv_decrement_weight;
    private TextView tv_increment_length,tv_decrement_length;
    private EditText et_weight,et_length,et_birthday;
    private Button btn_save;

    private float weight,length;
    private String gender,birthday;
    private ArrayList<BMI> bmis;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_signup2);

        //inflate
        rg_gender = findViewById(R.id.completeSignUp_rg_gender);
        tv_increment_weight = findViewById(R.id.completeSignUp_tv_increment_weight);
        tv_decrement_weight = findViewById(R.id.completeSignUp_tv_decrement_weight);
        tv_increment_length = findViewById(R.id.completeSignUp_tv_increment_length);
        tv_decrement_length = findViewById(R.id.completeSignUp_tv_decrement_length);
        et_weight = findViewById(R.id.completeSignUp_et_weight);
        et_length = findViewById(R.id.completeSignUp_et_length);
        et_birthday = findViewById(R.id.completeSignUp_et_birthday);
        btn_save = findViewById(R.id.completeSignUp_btn_save);

        //get data from CompleteSignUp activity
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");


        // + & - Buttons
        tv_increment_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_weight.getText().toString().isEmpty())
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


        // save button listener
        btn_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                gender=getSelectedGender(rg_gender);
                weight=Float.parseFloat(et_weight.getText().toString());
                length=Float.parseFloat(et_length.getText().toString());
                birthday=et_birthday.getText().toString();


                if(gender.equals("null") || birthday.isEmpty() || et_weight.getText().toString().isEmpty() || et_length.getText().toString().isEmpty() )
                    Toast.makeText(getBaseContext(),"please fill all fields",Toast.LENGTH_SHORT).show();
                else{



                    HashMap<String,Object> userDetails = new HashMap<>();
                    userDetails.put("gender",gender);
                    userDetails.put("birthday",birthday);
                    userDetails.put("length",et_length.getText().toString());
                    userDetails.put("weight",et_weight.getText().toString());


                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(userID)
                            .updateChildren(userDetails)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getBaseContext(),"Details Added Successfully",Toast.LENGTH_SHORT).show();

                                HashMap<String,Object> userRecords = new HashMap<>();
                                userRecords.put("weight",et_weight.getText().toString());
                                userRecords.put("length",et_length.getText().toString());
                                userRecords.put("date",java.time.LocalDate.now().toString());
                                userRecords.put("time",java.time.LocalTime.now().toString());

                                FirebaseDatabase.getInstance().getReference("Records").child(userID)
                                        .setValue(userRecords).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(getBaseContext(),"Record Added",Toast.LENGTH_LONG);
                                        }else
                                            Toast.makeText(getBaseContext(),"Failed"+task.getException().getMessage(),Toast.LENGTH_LONG);
                                    }
                                });

                                //home intent
                                Intent home_intent=new Intent(getBaseContext(),Home.class);
                                startActivity(home_intent);
                            }else{
                                Toast.makeText(getBaseContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }



    // get selected gender method
    public String getSelectedGender(RadioGroup rg_gender){
        String gender=null;
        int selected_gender_id=rg_gender.getCheckedRadioButtonId();
        if(selected_gender_id == -1){
            gender="null";
            return gender;
        }else{
            RadioButton selected_rb_gender=(RadioButton) findViewById(selected_gender_id);
            gender=selected_rb_gender.getText().toString();
            return  gender;
        }

    }



}




package com.saba.bmi;

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CompleteSignup2 extends AppCompatActivity {
    private RadioGroup rg_gender;
    private TextView tv_increment_weight,tv_decrement_weight;
    private TextView tv_increment_length,tv_decrement_length;
    private EditText et_weight,et_length,et_birthday;
    private Button btn_save;

    private float weight,length;
    private String gender,birthday;
    private String name,email,password;
    private User signed_user;
    private ArrayList<BMI> bmis;


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

        //get data from previous activity
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");


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
                    //create new user
                    LocalDate localDate= java.time.LocalDate.now();
                    LocalTime localTime= java.time.LocalTime.now();
                    BMI first_bmi=new BMI(weight,length,localDate,localTime);
                    bmis=new ArrayList<BMI>();
                    bmis.add(first_bmi);
                    signed_user=new User(name,email,password,gender,birthday,bmis);

                    //home intent
                    Intent home_intent=new Intent(getBaseContext(),Home.class);
                    home_intent.putExtra("signed_user", signed_user);
                    startActivity(home_intent);
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




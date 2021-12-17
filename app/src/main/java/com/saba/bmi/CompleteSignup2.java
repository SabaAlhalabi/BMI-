package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CompleteSignup2 extends AppCompatActivity {
    private RadioGroup rg_gender;
    private TextView tv_increment_weight,tv_decrement_weight;
    private TextView tv_increment_length,tv_decrement_length;
    private EditText et_weight,et_length,et_birthday;
    private Button btn_save;
    private double weight=0.00;
    private double length=0.00;
    private String gender,birthday;
    private final String NO_GENDER_SELECTED="NO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_signup2);

        //inflate
        rg_gender=findViewById(R.id.completeSignUp_rg_gender);
        tv_increment_weight=findViewById(R.id.completeSignUp_tv_increment_weight);
        tv_decrement_weight=findViewById(R.id.completeSignUp_tv_decrement_weight);
        tv_increment_length=findViewById(R.id.completeSignUp_tv_increment_length);
        tv_decrement_length=findViewById(R.id.completeSignUp_tv_decrement_length);
        et_weight=findViewById(R.id.completeSignUp_et_weight);
        et_length=findViewById(R.id.completeSignUp_et_length);
        et_birthday=findViewById(R.id.completeSignUp_et_birthday);
        btn_save=findViewById(R.id.completeSignUp_btn_save);

        et_weight.setText("0.00");
        et_length.setText("0.00");


        tv_increment_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight=Double.parseDouble(et_weight.getText().toString());
                weight=weight+1.00;
                et_weight.setText(Double.toString(weight));
            }
        });

        tv_decrement_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight=Double.parseDouble(et_weight.getText().toString());
                if(weight>0.00){
                weight=weight-1.00;
                et_weight.setText(Double.toString(weight));
                }
            }
        });

        tv_increment_length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                length=Double.parseDouble(et_length.getText().toString());
                length=length+1.00;
                et_length.setText(Double.toString(length));
            }
        });

        tv_decrement_length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(length>0.00){
                length=Double.parseDouble(et_length.getText().toString());
                length=length-1.00;
                et_length.setText(Double.toString(length));
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedGender(rg_gender);
                weight=Double.parseDouble(et_weight.getText().toString());
                length=Double.parseDouble(et_length.getText().toString());
                birthday=et_birthday.getText().toString();


                if(NO_GENDER_SELECTED.equals("No") || birthday.isEmpty() || (Double.toString(weight)).isEmpty() || (Double.toString(length)).isEmpty() )
                Toast.makeText(getBaseContext(),"please fill all fields",Toast.LENGTH_SHORT).show();
                else{
                    Intent home_intent=new Intent(getBaseContext(),Home.class);
                    startActivity(home_intent);
                }
            }
        });

    }





    public String getSelectedGender(RadioGroup rg_gender){
        int selected_gender_id=rg_gender.getCheckedRadioButtonId();
        if(selected_gender_id == -1){
          return NO_GENDER_SELECTED;
        }else{
            RadioButton selected_rb_gender=(RadioButton) findViewById(selected_gender_id);
            String gender_text=selected_rb_gender.getText().toString();
            return  gender;
        }

    }


}
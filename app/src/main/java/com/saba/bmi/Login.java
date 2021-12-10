package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText et_username,et_password;
    Button btn_login;
    TextView tv_signUp;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inflate
        et_username=findViewById(R.id.login_et_username);
        et_password=findViewById(R.id.login_et_password);
        btn_login=findViewById(R.id.login_btn_login);
        tv_signUp=findViewById(R.id.login_tv4_signUp);

        username=et_username.getText().toString();
        password=et_password.getText().toString();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Home.class);
                startActivity(i);
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Signup.class);
                startActivity(i);
            }

        });
    }



}
package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {
    EditText et_name,et_email,et_password,et_rePassword;
    TextView tv_login;
    Button btn_create;
    String name,email,password,rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //inflate
        et_name=findViewById(R.id.signup_et_name);
        et_email=findViewById(R.id.signup_et_email);
        et_password=findViewById(R.id.signup_et_password);
        et_rePassword=findViewById(R.id.signup_et_RePassword);
        tv_login=findViewById(R.id.signup_tv4_login);
        btn_create=findViewById(R.id.signup_btn_create);

        name=et_name.getText().toString();
        email=et_email.getText().toString();
        password=et_password.getText().toString();
        rePassword=et_rePassword.getText().toString();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),CompleteSignup2.class);
                startActivity(i);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Login.class);
                startActivity(i);
            }
        });
    }
}
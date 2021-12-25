package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText et_username,et_password;
    private Button btn_login;
    private TextView tv_signUp;
    private boolean isValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inflate
        et_username=findViewById(R.id.login_et_username);
        et_password=findViewById(R.id.login_et_password);
        btn_login=findViewById(R.id.login_btn_login);
        tv_signUp=findViewById(R.id.login_tv4_signUp);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=et_username.getText().toString();
                String password=et_password.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getBaseContext(),"please Fill the Required Fields!",Toast.LENGTH_SHORT).show();
                }else{
                    isValid=validate(username,password);
                    if (isValid){
                        Intent homeIntent =new Intent(Login.this,Home.class);
                        homeIntent.putExtra("name",username);
                        startActivity(homeIntent);
                    }else{
                        Toast.makeText(getBaseContext(),"Invalid Data, Try Again!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent =new Intent(Login.this,Signup.class);
                startActivity(signUpIntent);
            }

        });
    }

    public boolean validate(String username,String password){
        if(username.equals("admin") && password.equals("admin")){
            return true;
        }else return false;
    }

}
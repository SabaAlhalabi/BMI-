package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private EditText et_name,et_email,et_password,et_rePassword;
    private TextView tv_login;
    private Button btn_create;
    private String name,email,password,rePassword;
    private boolean isValidPassword,isValidEmail;

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


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=et_name.getText().toString();
                email=et_email.getText().toString();
                password=et_password.getText().toString();
                rePassword=et_rePassword.getText().toString();

                // check if all fields filled
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty())
                    Toast.makeText(getBaseContext(),"please fill all fields",Toast.LENGTH_SHORT).show();
                // email validation
                else if(!(isValidEmail=emailValidation(email)))
                Toast.makeText(getBaseContext(),"Email is not Valid",Toast.LENGTH_SHORT).show();
                else{
                    // Two passwords matches
                    isValidPassword=confirmPassword(password,rePassword);
                    if(!isValidPassword){
                        Toast.makeText(getBaseContext(),"The Entered Passwords Doesn't Match, Try Again!",Toast.LENGTH_SHORT).show();
                    }else{
                        //open Complete SignUp layout
                        Intent completeSignUpIntent= new Intent(Signup.this, CompleteSignUp2.class);
                        completeSignUpIntent.putExtra("name",name);
                        completeSignUpIntent.putExtra("email",email);
                        completeSignUpIntent.putExtra("password",password);
                        startActivity(completeSignUpIntent);
                    }
                }
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

    public boolean confirmPassword(String password,String rePassword){
        if(password.equals(rePassword)){
            return true;
        }
        else return false;
    }

    public boolean emailValidation(String email){
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(email);
        return m.find();
    }

}

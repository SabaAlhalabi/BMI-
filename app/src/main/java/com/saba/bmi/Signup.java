package com.saba.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
                    Toast.makeText(getBaseContext(),"please fill all fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                // email validation

                if(!(isValidEmail=emailValidation(email))) {
                    Toast.makeText(getBaseContext(), "Email is not Valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6){
                    et_password.setError("password should be 6 letters in minimum");
                    et_password.requestFocus();
                    return;
                }

                // Two passwords matches
                isValidPassword=confirmPassword(password,rePassword);
                if(!isValidPassword){
                    Toast.makeText(getBaseContext(),"The Entered Passwords Doesn't Match, Try Again!",Toast.LENGTH_SHORT).show();
                    return;
                }

               Intent completeSignupIntent = new Intent(getBaseContext(),CompleteSignup2.class);
                completeSignupIntent.putExtra("name",name);
                completeSignupIntent.putExtra("email",email);
                completeSignupIntent.putExtra("password",password);
                startActivity(completeSignupIntent);

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

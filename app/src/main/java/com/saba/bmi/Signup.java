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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private EditText et_name,et_email,et_password,et_rePassword;
    private TextView tv_login;
    private Button btn_create;
    private String name,email,password,rePassword;
    private boolean isValidPassword,isValidEmail;
    private FirebaseAuth  mAuth;

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
        mAuth=FirebaseAuth.getInstance();


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


                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    HashMap<String,Object> user = new HashMap<String, Object>();
                                    user.put("name",name);
                                    user.put("email",email);
                                    user.put("password",password);


                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(mAuth.getCurrentUser().getUid()).setValue(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(getBaseContext(),"User Registers Successfully",Toast.LENGTH_SHORT).show();
                                                        Intent completeSignupIntent = new Intent(getBaseContext(),CompleteSignup2.class);
                                                        completeSignupIntent.putExtra("userID",mAuth.getCurrentUser().getUid());;
                                                        startActivity(completeSignupIntent);
                                                    }else{
                                                        Toast.makeText(getBaseContext(),"Registration Failed, Try again!",Toast.LENGTH_SHORT).show();
                                                        System.out.println(task.getException().getMessage());
                                                    }
                                                }
                                            });
                                }else{
                                    Toast.makeText(getBaseContext(),"Registration Failed, Try again!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    System.out.println(task.getException().getMessage());
                                }
                            }
                        });

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

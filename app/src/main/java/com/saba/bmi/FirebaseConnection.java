package com.saba.bmi;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConnection {

    public static void signUp(User user, AppCompatActivity context){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user.setmAuth(mAuth);
        mAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    createUserData(context,user);
                else
                    Toast.makeText(context,"Error Adding user"+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void login(AppCompatActivity context){
        try {


        }catch (Exception e){
            Toast.makeText(context,"Invalid email or password",Toast.LENGTH_SHORT).show();
        }
    }

    private static void createUserData(AppCompatActivity context, User user) {
    }
}

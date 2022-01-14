package com.saba.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddFoodDetails extends AppCompatActivity {
    EditText et_name,et_category,et_calory;
    ImageView img_food;
    Button btn_upload, btn_save;
    String name,category,calory;
    FirebaseAuth mAuth;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_details);

        et_name=findViewById(R.id.addFoodDetails_et_name);
        et_calory=findViewById(R.id.addFoodDetails_et_calory);
        et_category=findViewById(R.id.addFoodDetails_et_category);
        img_food=findViewById(R.id.addFoodDetails_img_food);
        btn_save=findViewById(R.id.addFoodDetails_btn_save);
        btn_upload=findViewById(R.id.addFoodDetails_btn_upload);
        userId=mAuth.getCurrentUser().getUid();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=et_name.getText().toString();
                category=et_category.getText().toString();
                calory=et_calory.getText().toString();


                HashMap<String,Object> food = new HashMap<>();
                food.put("name",name);
                food.put("category",category);
                food.put("calory",calory);

                FirebaseDatabase.getInstance().getReference("Food").child(userId)
                        .setValue(food).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getBaseContext(),"Food Added",Toast.LENGTH_LONG);
                            Intent homeIntent=new Intent(getBaseContext(),Home.class);
                        }else
                            Toast.makeText(getBaseContext(),"Failed"+task.getException().getMessage(),Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }
}
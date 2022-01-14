package com.saba.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class FoodList extends AppCompatActivity {
    RecyclerView rv_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        rv_food = findViewById(R.id.foodList_rv_foods);
    }
}
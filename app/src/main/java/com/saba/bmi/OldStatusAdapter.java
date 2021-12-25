package com.saba.bmi;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OldStatusAdapter extends RecyclerView.Adapter<OldStatusAdapter.OldStatusViewHolder>{
    ArrayList<BMI> bmi_record;

    public OldStatusAdapter(ArrayList<BMI> bmi_record) {
        this.bmi_record = bmi_record;
    }

    @NonNull
    @Override
    public OldStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View status_custom_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_custom_item,null,false);
        OldStatusViewHolder holder = new OldStatusViewHolder(status_custom_item);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull OldStatusViewHolder holder, int position) {
        BMI current_bmi = bmi_record.get(position);
        holder.date.setText(current_bmi.getDate().toString());
        holder.weight.setText(Float.toString(current_bmi.getWeight()));
        holder.status.setText(current_bmi.getStatus());
        holder.length.setText(Float.toString(current_bmi.getLength()));

    }


    @Override
    public int getItemCount() {
        return bmi_record.size();
    }





    class OldStatusViewHolder extends RecyclerView.ViewHolder{
        TextView date,weight,status,length;
        public OldStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.status_custom_item_tv_date);
            weight=itemView.findViewById(R.id.status_custom_item_tv_weight);
            status=itemView.findViewById(R.id.status_custom_item_tv_status);
            length=itemView.findViewById(R.id.status_custom_item_tv_length);
        }
    }
}

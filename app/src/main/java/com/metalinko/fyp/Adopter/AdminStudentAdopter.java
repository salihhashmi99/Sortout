package com.metalinko.fyp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.metalinko.fyp.Admin.ShowAdminStudentComplainActivity;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.R;

import java.util.ArrayList;

public class AdminStudentAdopter extends RecyclerView.Adapter < AdminStudentAdopter.ViewHolder > {
    public ArrayList<StudentFormModel> arrayList;
    private Context context;

    // RecyclerView recyclerView;


    public AdminStudentAdopter(ArrayList<StudentFormModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recyclerview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText("System ID: " + arrayList.get(position).getSt_name());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =  new Intent(view.getContext() , ShowAdminStudentComplainActivity.class);
                intent.putExtra("key",arrayList.get(position).getSt_name());
                view.getContext().startActivity(intent);


            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
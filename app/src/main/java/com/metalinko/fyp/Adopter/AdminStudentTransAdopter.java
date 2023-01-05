package com.metalinko.fyp.Adopter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.metalinko.fyp.Admin.ShowAdminStudentTransportMainActivity;
import com.metalinko.fyp.Model.StudnetApplyForTranscriptModel;
import com.metalinko.fyp.R;

import java.util.ArrayList;

public class AdminStudentTransAdopter extends RecyclerView.Adapter < AdminStudentTransAdopter.ViewHolder > {
    public ArrayList<StudnetApplyForTranscriptModel> arrayList;
    private Context context;

    // RecyclerView recyclerView;


    public AdminStudentTransAdopter(ArrayList<StudnetApplyForTranscriptModel> arrayList, Context context) {
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

        holder.textView.setText("System ID: " + arrayList.get(position).getCINC());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =  new Intent(view.getContext() , ShowAdminStudentTransportMainActivity.class);
                intent.putExtra("key",arrayList.get(position).getCINC());
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
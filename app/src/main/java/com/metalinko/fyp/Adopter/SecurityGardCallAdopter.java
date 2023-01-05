package com.metalinko.fyp.Adopter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.metalinko.fyp.Model.SecurityGardCallModel;
import com.metalinko.fyp.R;

import java.util.ArrayList;

public class SecurityGardCallAdopter extends RecyclerView.Adapter <SecurityGardCallAdopter.ViewHolder > {
    public ArrayList<SecurityGardCallModel> arrayList;
    private Context context;

    // RecyclerView recyclerView;


    public SecurityGardCallAdopter(ArrayList<SecurityGardCallModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.securitygardcallrecyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(arrayList.get(position).getSd_name());
        holder.imageView.setText(arrayList.get(position).getPhone_number());

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView2);
            this.imageView = (TextView) itemView.findViewById(R.id.imageView3);
        }
    }
}
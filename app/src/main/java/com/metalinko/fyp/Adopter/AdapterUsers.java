package com.metalinko.fyp.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.metalinko.fyp.Model.UserModel;
import com.metalinko.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserModel> items = new ArrayList<>();
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, UserModel obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterUsers(Context context, List<UserModel> items) {
        this.items = items;
        this.ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_view;
        public TextView name_view;
        public TextView phone_view;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image_view = v.findViewById(R.id.image);
            name_view = v.findViewById(R.id.name_view);
            phone_view = v.findViewById(R.id.phone_view);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            UserModel u = items.get(position);
            view.name_view.setText(u.name);
            view.phone_view.setText(u.phone_number);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static final String TAG = "AdapterUsers";

}

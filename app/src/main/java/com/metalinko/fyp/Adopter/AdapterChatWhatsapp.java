package com.metalinko.fyp.Adopter;

import static com.metalinko.fyp.Tools.toTimeAgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.metalinko.fyp.Model.ChatMessage;
import com.metalinko.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterChatWhatsapp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int CHAT_ME = 100;
    private final int CHAT_YOU = 200;
    private List<ChatMessage> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, ChatMessage obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterChatWhatsapp(Context context) {
        this.ctx = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == CHAT_ME) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_whatsapp_me, parent, false);
            vh = new ItemViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_whatsapp_telegram_you, parent, false);
            vh = new ItemViewHolder(v);
        }
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            final ChatMessage m = items.get(position);
            ItemViewHolder vItem = (ItemViewHolder) holder;
            vItem.text_content.setText(m.body);

            if(m.receive_time == null){
                vItem.message_status.setImageResource(R.drawable.ic_processing);
            }else if(m.receive_time.equals("not_sent")){
                vItem.message_status.setImageResource(R.drawable.ic_processing);
            }else if(m.receive_time.equals("sent")){
                vItem.message_status.setImageResource(R.drawable.ic_check);
            }else if(m.receive_time.equals("downloaded")){
                vItem.message_status.setImageResource(R.drawable.ic_done);
            }else if(m.receive_time.equals("seen")){
                vItem.message_status.setImageResource(R.drawable.ic_complete);
            }
            vItem.text_time.setText( toTimeAgo( m.sent_time+"")
            );

            vItem.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, m, position);
                    }
                }
            });
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView text_content;
        public TextView text_time;
        public ImageView message_status;
        public View lyt_parent;

        public ItemViewHolder(View v) {
            super(v);
            text_content = v.findViewById(R.id.text_content);
            text_time = v.findViewById(R.id.text_time);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            message_status = v.findViewById(R.id.message_status);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).fromMe ? CHAT_ME : CHAT_YOU;
    }

    public void insertItem(ChatMessage item) {
        this.items.add(item);
        notifyItemInserted(getItemCount());
    }


    public void updateItems(List<ChatMessage> _items) {
        this.items = _items;
        notifyItemInserted(getItemCount());
    }

    public void setItems(List<ChatMessage> items) {
        this.items = items;
    }

}


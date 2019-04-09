package com.studioseven.dsc_chat.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studioseven.dsc_chat.Models.Chat;
import com.studioseven.dsc_chat.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private ArrayList<Chat> chatList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView messageTextView;
        public TextView timeTextView;
        public MyViewHolder(View v) {
            super(v);
            messageTextView = v.findViewById(R.id.textview_message);
            timeTextView = v.findViewById(R.id.textview_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(ArrayList<Chat> myDataset) {
        chatList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_bubble, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.messageTextView.setText(chatList.get(position).getMessageString());
        holder.timeTextView.setText(chatList.get(position).getTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void addMessage(Chat message){
        this.chatList.add(message);
        notifyDataSetChanged();
    }
}

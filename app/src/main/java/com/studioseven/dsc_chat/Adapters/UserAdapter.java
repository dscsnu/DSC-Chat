package com.studioseven.dsc_chat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studioseven.dsc_chat.ChatActivity;
import com.studioseven.dsc_chat.Models.User;
import com.studioseven.dsc_chat.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<User> userList;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout itemLin;
        ImageView userImage;
        TextView userNameTv;
        TextView userStatusTv;
        MyViewHolder(View v) {
            super(v);
            itemLin = v.findViewById(R.id.userItemLin);
            userImage = v.findViewById(R.id.userImage);
            userNameTv = v.findViewById(R.id.userName);
            userStatusTv = v.findViewById(R.id.userStatus);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter(ArrayList<User> myDataset, Context context) {
        userList = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Picasso.get().load(userList.get(position).getPhotoUrl()).into(holder.userImage);
        holder.userNameTv.setText(userList.get(position).getName());
        holder.userStatusTv.setText(userList.get(position).getStatus());
        holder.itemLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", userList.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addUser(User user){
        this.userList.add(user);
        notifyDataSetChanged();
    }
}


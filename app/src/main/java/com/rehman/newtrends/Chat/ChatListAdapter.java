package com.rehman.newtrends.Chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rehman.newtrends.R;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ChatListModel> mDataList;

    public ChatListAdapter(Context context, ArrayList<ChatListModel> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(context).inflate(R.layout.chatlist_list,parent,false);

        return new MyViewHolder(myView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ChatListModel model = mDataList.get(position);
        holder.text.setText(model.getChatType());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,ChatActivity.class);
            intent.putExtra("userUID",model.getSenderUID());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text =itemView.findViewById(R.id.text);
        }
    }


}

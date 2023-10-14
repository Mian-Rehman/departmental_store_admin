package com.rehman.newtrends.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rehman.newtrends.R;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    RecyclerView list_recycleView;
    ImageView back_image;
    ChatListAdapter adapter;
    ArrayList<ChatListModel> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        intiViews();
        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        mDataList = new ArrayList<>();
        adapter =new ChatListAdapter(this,mDataList);
        list_recycleView.setLayoutManager(new LinearLayoutManager(this));
        list_recycleView.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("adminChats");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ChatListModel model = snapshot.getValue(ChatListModel.class);
                mDataList.add(model);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void intiViews()
    {
        list_recycleView = findViewById(R.id.list_recycleView);
        back_image = findViewById(R.id.back_image);
    }
}
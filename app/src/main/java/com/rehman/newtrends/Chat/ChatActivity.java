package com.rehman.newtrends.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rehman.newtrends.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    RecyclerView chat_recycleView;
    ImageView back_image;
    EditText ed_massage;
    Button btn_send;

    String userUID;
    String chatType = "admin";
    ArrayList<ChatModel> mDataList;
    ChatAdapter adapter;
    String massage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
        getIntentValue();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_send.setOnClickListener(v -> {
            massage = ed_massage.getText().toString();

            if (massage.isEmpty())
            {
                ed_massage.setError("Type massage");
                ed_massage.requestFocus();
            }
            else
            {
                ed_massage.setText("");
                saveChatToDatabase(massage);
            }
        });
    }

    private void saveChatToDatabase(String massage)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userChats")
                .child(userUID).child("chats");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String key = reference.push().getKey();
                ChatModel  model = new ChatModel(massage,userUID,key,chatType);
                reference.child(key).setValue(model);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentValue()
    {
        Intent intent = getIntent();
       userUID = intent.getStringExtra("userUID");

       getMessages(userUID);
    }

    private void getMessages(String userUID)
    {
        mDataList = new ArrayList<>();
        adapter = new ChatAdapter(this,mDataList);
        chat_recycleView.setLayoutManager(new LinearLayoutManager(this));

        chat_recycleView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userChats")
                .child(userUID).child("chats");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if (snapshot.exists())
                {
                    ChatModel model = snapshot.getValue(ChatModel.class);
                    mDataList.add(model);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(ChatActivity.this, "No Chat Found", Toast.LENGTH_SHORT).show();
                }
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

    private void initViews()
    {
        chat_recycleView = findViewById(R.id.chat_recycleView);
        back_image = findViewById(R.id.back_image);
        ed_massage = findViewById(R.id.ed_massage);
        btn_send = findViewById(R.id.btn_send);
    }
}
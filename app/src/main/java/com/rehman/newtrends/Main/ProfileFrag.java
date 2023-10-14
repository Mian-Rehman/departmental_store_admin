package com.rehman.newtrends.Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rehman.newtrends.Chat.ChatActivity;
import com.rehman.newtrends.Chat.ChatListActivity;
import com.rehman.newtrends.R;


public class ProfileFrag extends Fragment {

    CardView createInventory_card,manageInventory_card,order_card,message_card;


    public ProfileFrag(String id) {
    }

    public ProfileFrag()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);

        manageInventory_card.setVisibility(View.GONE);

        createInventory_card.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),CreateInventoryActivity.class));
        });

        manageInventory_card.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),ManageInventoryActivity.class));
        });

        order_card.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),OrderActivity.class));
        });

        message_card.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChatListActivity.class));
        });

        return view;
    }

    private void initView(View view)
    {
        createInventory_card = view.findViewById(R.id.createInventory_card);
        manageInventory_card = view.findViewById(R.id.manageInventory_card);
        order_card = view.findViewById(R.id.order_card);
        message_card = view.findViewById(R.id.message_card);
    }
}
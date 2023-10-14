package com.rehman.newtrends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rehman.newtrends.Adapter.MianFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    MianFragmentAdapter adapter;
    ImageView bottom_image_profile;
    TextView bottom_text_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        adapter = new MianFragmentAdapter(this);
        viewPager.setUserInputEnabled(false);
        viewPager.setAdapter(adapter);


        bottom_image_profile.setImageResource(R.drawable.ic_user_active);
        bottom_text_profile.setTextColor(ContextCompat.getColor(this, R.color.orange));

        bottom_image_profile.setOnClickListener(v -> {
            viewPager.setCurrentItem(0, true);
        });

    }

    private void initView()
    {
        viewPager = findViewById(R.id.viewPager);
        bottom_image_profile = findViewById(R.id.bottom_image_profile);
        bottom_text_profile = findViewById(R.id.bottom_text_profile);
    }
}
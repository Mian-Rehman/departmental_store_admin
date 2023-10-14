package com.rehman.newtrends.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rehman.newtrends.Order.BookingTabsAdapter;
import com.rehman.newtrends.R;

public class OrderActivity extends AppCompatActivity {

    ViewPager2 booking_viewpager;
    LinearLayout booking_pending_layout,booking_cancel_layout,booking_success_layout;
    TextView booking_pending_text,booking_cancel_text,booking_success_text;
    View booking_pending_view,booking_cancel_view,booking_success_view;

    BookingTabsAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        booking_viewpager = findViewById(R.id.booking_viewpager);
        booking_pending_layout = findViewById(R.id.booking_pending_layout);
        booking_cancel_layout = findViewById(R.id.booking_cancel_layout);
        booking_success_layout = findViewById(R.id.booking_success_layout);
        booking_cancel_text = findViewById(R.id.booking_cancel_text);
        booking_pending_text = findViewById(R.id.booking_pending_text);
        booking_success_text = findViewById(R.id.booking_success_text);
        booking_pending_view = findViewById(R.id.booking_pending_view);
        booking_cancel_view = findViewById(R.id.booking_cancel_view);
        booking_success_view = findViewById(R.id.booking_success_view);

        adapter = new BookingTabsAdapter(this);
        booking_viewpager.setUserInputEnabled(true);
        booking_viewpager.setAdapter(adapter);

        booking_viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        booking_pending_text.setTextColor(ContextCompat.getColor(this,R.color.selected_font_color));
        booking_pending_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.selected_font_color,this.getTheme()));

        booking_cancel_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
        booking_cancel_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));

        booking_success_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
        booking_success_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));

        booking_pending_layout.setOnClickListener(v -> {
            booking_viewpager.setCurrentItem(0,true);
        });

        booking_cancel_layout.setOnClickListener(v -> {
            booking_viewpager.setCurrentItem(1,true);
        });

        booking_success_layout.setOnClickListener(v -> {
            booking_viewpager.setCurrentItem(2,true);
        });
    }

    private void onChangeTab(int position)
    {
        if (position == 0)
        {
            booking_pending_text.setTextColor(ContextCompat.getColor(this,R.color.selected_font_color));
            booking_pending_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.selected_font_color,this.getTheme()));

            booking_cancel_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
            booking_cancel_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));

            booking_success_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
            booking_success_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));
        }

        if (position == 1)
        {
            booking_pending_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
            booking_pending_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));

            booking_cancel_text.setTextColor(ContextCompat.getColor(this,R.color.selected_font_color));
            booking_cancel_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.selected_font_color,this.getTheme()));

            booking_success_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
            booking_success_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));
        }

        if (position == 2)
        {
            booking_pending_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
            booking_pending_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));

            booking_cancel_text.setTextColor(ContextCompat.getColor(this,R.color.gray));
            booking_cancel_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.gray,this.getTheme()));

            booking_success_text.setTextColor(ContextCompat.getColor(this,R.color.selected_font_color));
            booking_success_view.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.selected_font_color,this.getTheme()));
        }
    }
}
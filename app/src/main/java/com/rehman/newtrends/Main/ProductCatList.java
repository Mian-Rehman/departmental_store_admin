package com.rehman.newtrends.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rehman.newtrends.R;

public class ProductCatList extends AppCompatActivity implements View.OnClickListener {

    TextView smartWatch_text,woodenClock_text,womanFashion_text,gadgets_text,handfree_text,menTrouser_text
            ,rings_text,dataCable_text,makeUp_text,home_text,mobileAccessory_text;

    String selectCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cat_list);

        initView();

        smartWatch_text.setOnClickListener(this::onClick);
        woodenClock_text.setOnClickListener(this::onClick);
        womanFashion_text.setOnClickListener(this::onClick);
        gadgets_text.setOnClickListener(this::onClick);
        handfree_text.setOnClickListener(this::onClick);
        menTrouser_text.setOnClickListener(this::onClick);
        rings_text.setOnClickListener(this::onClick);
        dataCable_text.setOnClickListener(this::onClick);
        makeUp_text.setOnClickListener(this::onClick);
        home_text.setOnClickListener(this::onClick);
        mobileAccessory_text.setOnClickListener(this::onClick);
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v)
    {

        int id = v.getId();

        switch (id)
        {
            case R.id.smartWatch_text:
                selectCategory = smartWatch_text.getText().toString();
                break;

            case R.id.woodenClock_text:
                selectCategory = woodenClock_text.getText().toString();
                break;

            case R.id.womanFashion_text:
                selectCategory = womanFashion_text.getText().toString();
                break;

            case R.id.gadgets_text:
                selectCategory = gadgets_text.getText().toString();
                break;

            case R.id.handfree_text:
                selectCategory = handfree_text.getText().toString();
                break;

            case R.id.menTrouser_text:
                selectCategory = menTrouser_text.getText().toString();
                break;

            case R.id.rings_text:
                selectCategory = rings_text.getText().toString();
                break;

            case R.id.dataCable_text:
                selectCategory = dataCable_text.getText().toString();
                break;

            case R.id.makeUp_text:
                selectCategory = makeUp_text.getText().toString();
                break;

            case R.id.home_text:
                selectCategory = home_text.getText().toString();
                break;

            case R.id.mobileAccessory_text:
                selectCategory = mobileAccessory_text.getText().toString();
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("categoryListIntent", selectCategory);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void initView()
    {
        smartWatch_text = findViewById(R.id.smartWatch_text);
        woodenClock_text = findViewById(R.id.woodenClock_text);
        womanFashion_text = findViewById(R.id.womanFashion_text);
        gadgets_text = findViewById(R.id.gadgets_text);
        handfree_text = findViewById(R.id.handfree_text);
        menTrouser_text = findViewById(R.id.menTrouser_text);
        rings_text = findViewById(R.id.rings_text);
        dataCable_text = findViewById(R.id.dataCable_text);
        makeUp_text = findViewById(R.id.makeUp_text);
        home_text = findViewById(R.id.home_text);
        mobileAccessory_text = findViewById(R.id.mobileAccessory_text);
    }
}
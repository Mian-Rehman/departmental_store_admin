package com.rehman.newtrends.Order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rehman.newtrends.R;

public class ViewBookingDetailsActivity extends AppCompatActivity {

    ImageView cover_Image;
    TextView orderType_text,orderKey_text,firstName_text,email_text,phone_text,address_text;

    String userUID,productKey,productName,productPrice,productDiscountPrice,productDescription,productCoverImage
            ,phoneNumber,orderType,orderKey,firstName,lastName,email,city,state,addressOne,addressTwo;

    TextView pending_text,cancel_text,success_text;
    String response,customerKey;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking_details);

        intiViws();
        getIntentValue();

        pending_text.setOnClickListener(v -> {

            progressDialog = ProgressDialog.show(this, "", "Processing", true);

            pending_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.selected_font_color));
            pending_text.setTextColor(ContextCompat.getColor(this,R.color.white));

            cancel_text.setBackground(ContextCompat.getDrawable(this, R.drawable.border_layout));
            cancel_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
            cancel_text.setTextColor(ContextCompat.getColor(this,R.color.black));

            success_text.setBackground(ContextCompat.getDrawable(this, R.drawable.border_layout));
            success_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
            success_text.setTextColor(ContextCompat.getColor(this,R.color.black));

            response  = "pending";
            saveResponseToDataBase(response);
        });

        cancel_text.setOnClickListener(v -> {

            progressDialog = ProgressDialog.show(this, "", "Processing", true);

            pending_text.setBackground(ContextCompat.getDrawable(this, R.drawable.border_layout));
            pending_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
            pending_text.setTextColor(ContextCompat.getColor(this,R.color.black));

            cancel_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.selected_font_color));
            cancel_text.setTextColor(ContextCompat.getColor(this,R.color.white));

            success_text.setBackground(ContextCompat.getDrawable(this, R.drawable.border_layout));
            success_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
            success_text.setTextColor(ContextCompat.getColor(this,R.color.black));

            response  = "cancel";
            saveResponseToDataBase(response);
        });

        success_text.setOnClickListener(v -> {

            progressDialog = ProgressDialog.show(this, "", "Processing", true);

            pending_text.setBackground(ContextCompat.getDrawable(this, R.drawable.border_layout));
            pending_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
            pending_text.setTextColor(ContextCompat.getColor(this,R.color.black));

            cancel_text.setBackground(ContextCompat.getDrawable(this, R.drawable.border_layout));
            cancel_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
            cancel_text.setTextColor(ContextCompat.getColor(this,R.color.black));

            success_text.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.selected_font_color));
            success_text.setTextColor(ContextCompat.getColor(this,R.color.white));

            response  = "success";
            saveResponseToDataBase(response);

        });
    }

    private void saveResponseToDataBase(String response)
    {

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("ordersAdmin").child(orderKey);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    reference.child("orderType").setValue(response);

                    saveCustomerResponseData(response);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void saveCustomerResponseData(String response)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("orders").child(userUID)
                .child(orderKey);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    reference.child("orderType").setValue(response);
                    progressDialog.dismiss();
                }
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
        productKey = intent.getStringExtra("productKey");
        productName = intent.getStringExtra("productName");
        productPrice = intent.getStringExtra("productPrice");
        productDiscountPrice = intent.getStringExtra("productDiscountPrice");
        productDescription = intent.getStringExtra("productDescription");
        productCoverImage = intent.getStringExtra("productCoverImage");
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        phoneNumber = intent.getStringExtra("phoneNumber");
        addressOne = intent.getStringExtra("addressOne");
        addressTwo = intent.getStringExtra("addressTwo");
        city = intent.getStringExtra("city");
        state = intent.getStringExtra("state");
        orderType = intent.getStringExtra("orderType");
        orderKey = intent.getStringExtra("orderKey");

        Glide.with(this).load(productCoverImage).into(cover_Image);
        orderKey_text.setText("Order Status: "+orderType);
        orderKey_text.setText("Order Key: "+orderKey);
        firstName_text.setText("Name: "+firstName+" "+lastName);
        email_text.setText(email);
        phone_text.setText(phoneNumber);
        address_text.setText(addressOne);

    }

    private void intiViws()
    {
        cover_Image = findViewById(R.id.cover_Image);
        orderType_text = findViewById(R.id.orderType_text);
        orderKey_text = findViewById(R.id.orderKey_text);
        firstName_text = findViewById(R.id.firstName_text);
        email_text = findViewById(R.id.email_text);
        phone_text = findViewById(R.id.phone_text);
        address_text = findViewById(R.id.address_text);

        pending_text = findViewById(R.id.pending_text);
        cancel_text = findViewById(R.id.cancel_text);
        success_text = findViewById(R.id.success_text);
    }
}
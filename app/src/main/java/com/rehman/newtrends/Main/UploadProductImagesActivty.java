package com.rehman.newtrends.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rehman.newtrends.Adapter.MultiImageAdapter;
import com.rehman.newtrends.R;

import java.util.ArrayList;
import java.util.HashMap;

public class UploadProductImagesActivty extends AppCompatActivity {


    ImageView back_image;
    RecyclerView image_recyclerView;
    ArrayList<Uri> uriImageList = new ArrayList<>();
    MultiImageAdapter adapter;
    TextView save_text,photoCount_text;
    ImageView open_image;
    private static final int READ_PERMISSION = 101;

    String productCategory,productTitle,productDescription,productPrice,productDiscountPrice,productColor,productKey;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    StorageReference storageReference;
    private int UPLOAD_COUNT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product_images_activty);

        initView();
        getIntentData();

        save_text.setVisibility(View.INVISIBLE);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Images");

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        adapter = new MultiImageAdapter(this,uriImageList);
        image_recyclerView.setLayoutManager(new GridLayoutManager(UploadProductImagesActivty.this,4));
        image_recyclerView.setAdapter(adapter);

        if(ContextCompat.checkSelfPermission(UploadProductImagesActivty.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(UploadProductImagesActivty.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },READ_PERMISSION);
        }

        open_image.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"select Picture"),1);

            save_text.setVisibility(View.VISIBLE);

        });

        save_text.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(UploadProductImagesActivty.this, "Please wait", "Processing", true);
            saveDataToFirebaseStorage();

        });
    }

    private void saveDataToFirebaseStorage()
    {
        StorageReference storage = storageReference.child(productCategory).child(productKey);

        for (UPLOAD_COUNT = 0; UPLOAD_COUNT < uriImageList.size(); UPLOAD_COUNT++)
        {
            Uri imageUri = uriImageList.get(UPLOAD_COUNT);
            StorageReference store = storage.child("productImages"+imageUri.getLastPathSegment());

            store.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url =  uri.toString();
                            progressDialog.dismiss();
                            storeLink(url);
                        }
                    });
                }
            });
        }
    }

    private void storeLink(String url)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products");
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("productImages",url);
        reference.child(productCategory).child(productKey).child("image").push().setValue(hashMap);
        progressDialog.dismiss();
    }

    private void getIntentData()
    {

        productCategory = getIntent().getStringExtra("productCategory");
        productTitle = getIntent().getStringExtra("productTitle");
        productDescription = getIntent().getStringExtra("productDescription");
        productPrice = getIntent().getStringExtra("productPrice");
        productDiscountPrice = getIntent().getStringExtra("productDiscountPrice");
        productColor = getIntent().getStringExtra("productColor");
        productKey =  getIntent().getStringExtra("productKey");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            if (data.getClipData() != null)
            {
                int x = data.getClipData().getItemCount();

                for (int i=0; i<x;i++)
                {
                    uriImageList.add(data.getClipData().getItemAt(i).getUri());
                }
                adapter.notifyDataSetChanged();
                photoCount_text.setText("Photo (" + uriImageList.size()+")");
            }
            else if (data.getClipData() != null)
            {
                String imageURL = data.getData().getPath();
                uriImageList.add(Uri.parse(imageURL));
            }
        }
    }


    private void initView()
    {
        image_recyclerView = findViewById(R.id.image_recyclerView);
        save_text = findViewById(R.id.save_text);
        photoCount_text = findViewById(R.id.photoCount_text);
        open_image = findViewById(R.id.open_image);
        back_image = findViewById(R.id.back_image);
    }
}
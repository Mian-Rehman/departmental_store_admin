package com.rehman.newtrends.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rehman.newtrends.Model.ProductDataModel;
import com.rehman.newtrends.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateInventoryActivity extends AppCompatActivity {

    TextView productCategory_text,categoryColor_text;
    EditText productTitle_ed,productDes_ed,Charges_ed,ChargesDiscount_ed;
    Button next_button;
    String catValue;
    String key;
    ProgressDialog progressDialog;
    String productCategory,productTitle,productDescription,productPrice,productDiscountPrice,productColor;
    ImageView productCoverPhoto_image,back_image;
    Uri uri;
    Bitmap bitmap;
    String coverTextUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    String profileImageUrl;

    @Override
    protected void onResume() {
        super.onResume();
        if (progressDialog!=null)
        {
            progressDialog.dismiss();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inventory);

        initView();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Images");

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        categoryColor_text.setOnClickListener(v -> {

//            Intent intent = new Intent(this, ProductColorListActivity.class);
//            startActivityForResult(intent, 2);
            Toast.makeText(this, "under Developed", Toast.LENGTH_SHORT).show();
        });

        productCoverPhoto_image.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 16);
        });

        productCategory_text.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProductCatList.class);
            startActivityForResult(intent, 1);
        });

        next_button.setOnClickListener(v -> {

            progressDialog = ProgressDialog.show(CreateInventoryActivity.this, "Please wait", "Processing", true);
             productCategory = productCategory_text.getText().toString();
             productTitle = productTitle_ed.getText().toString().trim();
             productDescription = productDes_ed.getText().toString().trim();
             productPrice = Charges_ed.getText().toString().trim();
             productDiscountPrice = ChargesDiscount_ed.getText().toString().trim();
             productColor = categoryColor_text.getText().toString().trim();

            if (isValid(productCategory,productTitle,productDescription,productPrice,productDiscountPrice,productColor))
            {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                         key = reference.push().getKey().toString();
                         saveProfileImage(key);
                        ProductDataModel model = new ProductDataModel(productCategory, productTitle,
                                productDescription,productPrice,productDiscountPrice
                                ,productColor,key,profileImageUrl);

                        reference.child(productCategory).child(key).setValue(model);
                        nextInent(key);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });



    }

    private void saveProfileImage(String key)
    {
        StorageReference storage = storageReference.child(key);

        StorageReference store = storage.child("profileImage"+uri.getLastPathSegment());

        store.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                         profileImageUrl =  uri.toString();
                         saveUrl(profileImageUrl,key);

                        progressDialog.dismiss();
                        Log.d("profileeee", "onSuccess: "+profileImageUrl);

                    }
                });
            }
        });
    }

    private void saveUrl(String profileImageUrl, String key)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products");
        ProductDataModel model = new ProductDataModel(productCategory, productTitle,
                productDescription,productPrice,productDiscountPrice
                ,productColor,key,profileImageUrl);

        reference.child(productCategory).child(key).setValue(model);

    }


    private void nextInent(String key)
    {
        Intent nextIntent = new Intent(CreateInventoryActivity.this,UploadProductImagesActivty.class);
                nextIntent.putExtra("productCategory",productCategory);
                nextIntent.putExtra("productTitle",productTitle);
                nextIntent.putExtra("productDescription",productDescription);
                nextIntent.putExtra("productPrice",productPrice);
                nextIntent.putExtra("productDiscountPrice",productDiscountPrice);
                nextIntent.putExtra("productColor",productColor);
                nextIntent.putExtra("productKey",key);
                startActivity(nextIntent);

    }

    private boolean isValid(String productCategory, String productTitle, String productDescription, String productPrice,
                            String productDiscountPrice, String productColor)
    {
        if (productCategory.isEmpty())
        {
            Toast.makeText(this, "Select Category", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return false;
        }

        if (productTitle.isEmpty())
        {
            productTitle_ed.setError("required");
            productTitle_ed.requestFocus();
            progressDialog.dismiss();
            return false;
        }

        if (productDescription.isEmpty())
        {
            productDes_ed.setError("required");
            productDes_ed.requestFocus();
            progressDialog.dismiss();
            return false;
        }

        if (productPrice.isEmpty())
        {
            Charges_ed.setError("required");
            Charges_ed.requestFocus();
            progressDialog.dismiss();
            return false;
        }

        return true;
    }

    private void initView()
    {
        productCategory_text = findViewById(R.id.productCategory_text);
        productTitle_ed = findViewById(R.id.productTitle_ed);
        productDes_ed = findViewById(R.id.productDes_ed);
        Charges_ed = findViewById(R.id.Charges_ed);
        ChargesDiscount_ed = findViewById(R.id.ChargesDiscount_ed);
        categoryColor_text = findViewById(R.id.categoryColor_text);
        next_button = findViewById(R.id.next_button);
        productCoverPhoto_image = findViewById(R.id.productCoverPhoto_image);
        back_image = findViewById(R.id.back_image);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                catValue = data.getStringExtra("categoryListIntent");
                productCategory_text.setText(catValue);
            }
        }

//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK)
//        {
//            Uri imageUri = CropImage.getPickImageResultUri(this,data);
//            if (CropImage.isReadExternalStoragePermissionsRequired(this,imageUri))
//            {
//                uri = imageUri;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
//            }
//            else
//            {
//                startCrop(imageUri);
//            }
//        }
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
//        {
//            CropImage.ActivityResult result =  CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK)
//            {
//                productCoverPhoto_image.setImageURI(result.getUri());
//                Log.d("coverImage", ": "+result.getUri());
//            }
//        }

        if (requestCode == 16 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bytes = stream.toByteArray();
                coverTextUri = Base64.encodeToString(bytes, Base64.DEFAULT);
                productCoverPhoto_image.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

//    private void startCrop(Uri imageUri)
//    {
//        CropImage.activity(imageUri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(this);
//    }
    }
}
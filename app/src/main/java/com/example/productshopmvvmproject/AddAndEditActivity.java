package com.example.productshopmvvmproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.productshopmvvmproject.databinding.ActivityAddAndEditBinding;
import com.example.productshopmvvmproject.model.DataConverter;
import com.example.productshopmvvmproject.model.Product;
import com.example.productshopmvvmproject.viewmodel.MainActivityViewModel;

import java.io.File;

public class AddAndEditActivity extends AppCompatActivity {
    private Product product;
    public static final String Product_ID = "productId";
    public static final String Product_Name = "productName";
    public static final String Product_Description = "productDescription";
    public static final String Product_RegularPrice = "productRegularPrice";
    public static final String Product_SalesPrice = "productSalesPrice";
    public static final String Product_Photo = "productPhoto";
    public static final String Product_Colors = "productColors";
    public static final String Product_Stores = "productStores";
    private ActivityAddAndEditBinding activityAddAndEditBinding;
    private AddAndEditActivityClickHandlers addAndEditActivityClickHandlers;
    private MainActivityViewModel mainActivityViewModel;
    private static final int PICKFILE_RESULT_CODE = 10;
    File source,destination;
    Bitmap bmpImage;
    public static final int DELETE_PRODUCT_REQUEST_CODE=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        product = new Product();
        activityAddAndEditBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_and_edit);
        activityAddAndEditBinding.setProduct(product);

        addAndEditActivityClickHandlers = new AddAndEditActivityClickHandlers(this);
        activityAddAndEditBinding.setClickHandler(addAndEditActivityClickHandlers);

        Intent intent = getIntent();

        if(intent.hasExtra(Product_ID))
        {
            setTitle("Edit Product");
            product.setId(Integer.parseInt(intent.getStringExtra(Product_ID)));
            product.setProductName(intent.getStringExtra(Product_Name));
            product.setProductDescription(intent.getStringExtra(Product_Description));
            product.setRegularPrice(intent.getStringExtra(Product_RegularPrice));
            product.setSalePrice(intent.getStringExtra(Product_SalesPrice));
            product.setProductPhoto(intent.getStringExtra(Product_Photo));
            product.setColors(intent.getStringExtra(Product_Colors));
            product.setStores(intent.getStringExtra(Product_Stores));
        }else{
            setTitle("Add New Book");
        }

    }
    public class AddAndEditActivityClickHandlers{
        Context context;

        public AddAndEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){

            if (product.getProductName() == null){
                Toast.makeText(context, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent();
                intent.putExtra(Product_Name,product.getProductName());
                intent.putExtra(Product_Description,product.getProductDescription());
                intent.putExtra(Product_RegularPrice,product.getRegularPrice());
                intent.putExtra(Product_SalesPrice,product.getSalePrice());
                intent.putExtra(Product_Photo, product.getProductPhoto());
                intent.putExtra(Product_Colors,product.getColors());
                intent.putExtra(Product_Stores,product.getStores());
                setResult(RESULT_OK,intent);
                finish();
            }

        }

        public void onDeleteButtonClicked(View view){

            Intent intent = new Intent();
            intent.putExtra(Product_Name,product.getProductName());
            intent.putExtra(Product_Description,product.getProductDescription());
            intent.putExtra(Product_RegularPrice,product.getRegularPrice());
            intent.putExtra(Product_SalesPrice,product.getSalePrice());
            intent.putExtra(Product_Photo, product.getProductPhoto());
            intent.putExtra(Product_Colors,product.getColors());
            intent.putExtra(Product_Stores,product.getStores());
            setResult(RESULT_FIRST_USER,intent);
            finish();
        }

        public void onImageButtonClicked(View view){


            Intent chooseFile = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
//            chooseFile.setType("image/*");
          //  Intent.createChooser(chooseFile, "Choose a file")
            startActivityForResult(chooseFile , PICKFILE_RESULT_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_RESULT_CODE && resultCode == Activity.RESULT_OK){

//            bmpImage = (Bitmap) data.getExtras().get("data");
//            if(bmpImage != null){
//                imageView.setImageBitmap(bmpImage);
//            }

            Uri content_describer = data.getData();
           // imageView.setImageURI(content_describer);
//            String src = content_describer.getPath();
//            source = new File(src);
//            Log.d("src is ", source.toString());
//            String filename = content_describer.getLastPathSegment();
//            Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
//            //txt_pathshow.setText(filename);
//            Log.d("FileName is ",filename);
//            destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Test/TestTest/" + filename);
//            Log.d("Destination is ", destination.toString());
            //SetToFolder.setEnabled(true);
        }
    }
}

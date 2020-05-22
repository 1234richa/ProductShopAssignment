package com.example.productshopmvvmproject;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productshopmvvmproject.databinding.ActivityMainBinding;
import com.example.productshopmvvmproject.model.DataConverter;
import com.example.productshopmvvmproject.model.Product;
import com.example.productshopmvvmproject.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.*;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Product> productsList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private RecyclerView productsRecyclerView;
    private ProductsAdapter productsAdapter;
    private int selectedProductId;


    public static final int ADD_PRODUCT_REQUEST_CODE=1;
    public static final int EDIT_PRODUCT_REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Product Shop");

        checkImageReadPermissions();

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers=new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);
        setSupportActionBar(activityMainBinding.toolbar);

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {

                productsList=(ArrayList<Product>)products;
                for(Product p:products){

                    Log.i("MyTAG",p.getProductName());
                }
                loadBooksArrayList();
            }
        });

    }

    public class MainActivityClickHandlers{

        public void onFABClicked(View view){

            Intent intent = new Intent(MainActivity.this,AddAndEditActivity.class);
            startActivityForResult(intent,ADD_PRODUCT_REQUEST_CODE);
        }

    }
    private void loadBooksArrayList (){
        mainActivityViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                productsList = (ArrayList<Product>) products;
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView(){

        productsRecyclerView = activityMainBinding.secondaryLayout.rvProducts;
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsRecyclerView.setHasFixedSize(true);

        productsAdapter = new ProductsAdapter();
        productsAdapter.setProducts(productsList);
        productsRecyclerView.setAdapter(productsAdapter);

        productsAdapter.setListner(new ProductsAdapter.OnItemClickListner() {
            @Override
            public void onItemclick(Product product) {
                selectedProductId = product.getId();
                Intent intent = new Intent(MainActivity.this,AddAndEditActivity.class);
                intent.putExtra(AddAndEditActivity.Product_ID,""+ product.getId());
                intent.putExtra(AddAndEditActivity.Product_Name,product.getProductName());
                intent.putExtra(AddAndEditActivity.Product_Description,product.getProductDescription());
                intent.putExtra(AddAndEditActivity.Product_RegularPrice,product.getRegularPrice());
                intent.putExtra(AddAndEditActivity.Product_SalesPrice,product.getSalePrice());
                intent.putExtra(AddAndEditActivity.Product_Photo,product.getProductPhoto());
                intent.putExtra(AddAndEditActivity.Product_Colors,product.getColors());
                intent.putExtra(AddAndEditActivity.Product_Stores,product.getStores());
                startActivityForResult(intent,EDIT_PRODUCT_REQUEST_CODE);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK){

            Product product = new Product();
            product.setProductName(data.getStringExtra(AddAndEditActivity.Product_Name));
            product.setProductDescription(data.getStringExtra(AddAndEditActivity.Product_Description));
            product.setRegularPrice(data.getStringExtra(AddAndEditActivity.Product_RegularPrice));
            product.setSalePrice(data.getStringExtra(AddAndEditActivity.Product_SalesPrice));
            product.setProductPhoto(data.getStringExtra(AddAndEditActivity.Product_Photo));
            product.setColors(data.getStringExtra(AddAndEditActivity.Product_Colors));
            product.setStores(data.getStringExtra(AddAndEditActivity.Product_Stores));
            mainActivityViewModel.addNewProduct(product);

        }else if(requestCode == EDIT_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK){
            Product product = new Product();
            product.setProductName(data.getStringExtra(AddAndEditActivity.Product_Name));
            product.setProductDescription(data.getStringExtra(AddAndEditActivity.Product_Description));
            product.setRegularPrice(data.getStringExtra(AddAndEditActivity.Product_RegularPrice));
            product.setSalePrice(data.getStringExtra(AddAndEditActivity.Product_SalesPrice));
            product.setProductPhoto(data.getStringExtra(AddAndEditActivity.Product_Photo));
            product.setColors(data.getStringExtra(AddAndEditActivity.Product_Colors));
            product.setStores(data.getStringExtra(AddAndEditActivity.Product_Stores));
            product.setId(selectedProductId);
            mainActivityViewModel.updateNewProduct(product);

        }else if(requestCode == EDIT_PRODUCT_REQUEST_CODE && resultCode == RESULT_FIRST_USER){
            Product product = new Product();
            product.setProductName(data.getStringExtra(AddAndEditActivity.Product_Name));
            product.setProductDescription(data.getStringExtra(AddAndEditActivity.Product_Description));
            product.setRegularPrice(data.getStringExtra(AddAndEditActivity.Product_RegularPrice));
            product.setSalePrice(data.getStringExtra(AddAndEditActivity.Product_SalesPrice));
            product.setProductPhoto(data.getStringExtra(AddAndEditActivity.Product_Photo));
            product.setColors(data.getStringExtra(AddAndEditActivity.Product_Colors));
            product.setStores(data.getStringExtra(AddAndEditActivity.Product_Stores));
            product.setId(selectedProductId);
            mainActivityViewModel.deleteNewProduct(product);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkImageReadPermissions() {
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            ArrayList<String> neededPermissions = new ArrayList<>();
            int checkInternal =checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (checkInternal != PackageManager.PERMISSION_GRANTED) {
                neededPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (neededPermissions.size() > 0) {
                requestPermissions(neededPermissions.toArray(new String[neededPermissions.size()]), 111);
            }

        }
    }
}

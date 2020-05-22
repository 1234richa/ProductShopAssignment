package com.example.productshopmvvmproject.model;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.productshopmvvmproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {Product.class},version = 1)
public abstract class ProductsDatabase extends RoomDatabase {

    public abstract ProductDAO productDAO();
    private static ProductsDatabase instance;
    private static Context activity;

    public static synchronized ProductsDatabase getInstance(Context context) {

        activity = context.getApplicationContext();

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductsDatabase.class, "products_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDAO productDAO;

        public InitialDataAsyncTask(ProductsDatabase productsDatabase) {
            productDAO=  productsDatabase.productDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Product product1=new Product();
            product1.setProductName("Books");
            product1.setProductDescription("Text Books Description Text Books Description Text Books Description Text Books Description");
            product1.setRegularPrice("100");
            //product1.setProductPhoto("pic");
            product1.setSalePrice("200");
            product1.setColors("RED");
            product1.setStores("Store1");

            Product product2=new Product();
            product2.setProductName("Story Books");
            product2.setProductDescription("Story Books Description");
            product2.setRegularPrice("100");
           // product2.setProductPhoto("pic");
            product2.setSalePrice("200");
            product2.setColors("RED");
            product2.setStores("Store1");

            productDAO.insert(product1);
            productDAO.insert(product2);

            fillWithStartingData(activity);

            return null;
        }
    }

    private static void fillWithStartingData(Context context){
        ProductDAO productDAO = getInstance(context).productDAO();

        JSONArray products = loadJSONArray(context);

        try {
            for (int i=0; i< products.length(); i++){
                JSONObject productObj = products.getJSONObject(i);

                String productName = productObj.getString("productName");
                String productDescription = productObj.getString("productDescription");
                String regularPrice = productObj.getString("regularPrice");
                String salePrice = productObj.getString("salePrice");
                String producPhoto = productObj.getString("productPhoto");
                String productColor = productObj.getString("colors");
                String productStore = productObj.getString("stores");

                Product product=new Product();
                product.setProductName(productName);
                product.setProductDescription(productDescription);
                product.setRegularPrice(regularPrice);
                product.setProductPhoto(producPhoto);
                product.setSalePrice(salePrice);
                product.setColors(productColor);
                product.setStores(productStore);
                productDAO.insert(product);
            }

        }catch (JSONException e){

        }
    }

    private static JSONArray loadJSONArray(Context context){
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.product_list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;

        try{
            while ((line = reader.readLine()) !=null){
                builder.append(line);
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            return jsonObject.getJSONArray("products");

        }catch (IOException | JSONException exception){
            exception.printStackTrace();
        }
        return null;
    }


}

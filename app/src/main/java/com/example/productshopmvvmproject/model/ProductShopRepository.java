package com.example.productshopmvvmproject.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProductShopRepository {
    private ProductDAO productDAO;

    private LiveData<List<Product>> products;

    public ProductShopRepository(Application application) {
        ProductsDatabase productsDatabase = ProductsDatabase.getInstance(application);
        productDAO = productsDatabase.productDAO();
        }

     public LiveData<List<Product>> getProducts(){
        return productDAO.getAllProducts();
     }

     public void insertProduct(final Product product){
        //new InsertProductAsyncTask(productDAO).execute(product);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDAO.insert(product);
            }
        });
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product,Void,Void> {
        private ProductDAO productDAO;

        public InsertProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.insert(products [0]);
            return null;
        }
    }

    public void deleteProduct(Product product){
        new DeleteProductAsyncTask(productDAO).execute(product);
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product,Void,Void> {
        private ProductDAO productDAO;

        public DeleteProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.delete(products [0]);
            return null;
        }
    }

    public void updateProduct(Product product){
        new UpdateProductAsyncTask(productDAO).execute(product);
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product,Void,Void> {
        private ProductDAO productDAO;

        public UpdateProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.update(products [0]);
            return null;
        }
    }

}

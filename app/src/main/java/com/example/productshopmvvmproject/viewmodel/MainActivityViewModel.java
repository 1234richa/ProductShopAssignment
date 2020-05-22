package com.example.productshopmvvmproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.productshopmvvmproject.model.Product;
import com.example.productshopmvvmproject.model.ProductShopRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private ProductShopRepository productShopRepository;
    private LiveData<List<Product>> allProducts;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        productShopRepository = new ProductShopRepository(application);
    }

    public LiveData<List<Product>> getAllProducts() {
        allProducts = productShopRepository.getProducts();
        return allProducts;
    }

    public void  addNewProduct(Product product){
        productShopRepository.insertProduct(product);
    }

    public void deleteNewProduct(Product product){
        productShopRepository.deleteProduct(product);
    }

    public void updateNewProduct(Product product) {
        productShopRepository.updateProduct(product);
    }
}

package com.example.productshopmvvmproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.productshopmvvmproject.databinding.ProductListItemBinding;
import com.example.productshopmvvmproject.model.Product;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

    private OnItemClickListner listner;

    private ArrayList<Product> products = new ArrayList<Product>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ProductListItemBinding productListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(productListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = products.get(i);
        productViewHolder.productListItemBinding.setProduct(product);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(ArrayList<Product> newProducts) {
        //this.books = books;
        //notifyDataSetChanged();
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ProductsDiffCallback(products,newProducts),false);
        products = newProducts;
        result.dispatchUpdatesTo(ProductsAdapter.this);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        private ProductListItemBinding productListItemBinding;

        public ProductViewHolder(@NonNull ProductListItemBinding productListItemBinding) {
            super(productListItemBinding.getRoot());
            this.productListItemBinding = productListItemBinding;
            productListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int clickedPosition = getAdapterPosition();
                    if (listner !=null && clickedPosition!=RecyclerView.NO_POSITION) {

                        listner.onItemclick(products.get(clickedPosition));
                    }

                }
            });
        }
    }

    public interface OnItemClickListner{
        void onItemclick(Product product);
    }

    public void setListner(OnItemClickListner listner) {
        this.listner = listner;
    }


}

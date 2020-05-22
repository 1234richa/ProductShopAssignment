package com.example.productshopmvvmproject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.productshopmvvmproject.model.Product;

import java.util.ArrayList;

public class ProductsDiffCallback extends DiffUtil.Callback {

    ArrayList<Product> oldProductsList;
    ArrayList<Product> newProductsList;

    public ProductsDiffCallback(ArrayList<Product> oldProductsList, ArrayList<Product> newProductsList) {
        this.oldProductsList = oldProductsList;
        this.newProductsList = newProductsList;
    }

    @Override
    public int getOldListSize() {
        return oldProductsList==null?0:oldProductsList.size();
    }

    @Override
    public int getNewListSize() {
        return newProductsList==null?0:newProductsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProductsList.get(oldItemPosition).getId()==newProductsList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProductsList.get(oldItemPosition).equals (newProductsList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}

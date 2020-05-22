package com.example.productshopmvvmproject.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.Objects;

@Entity(tableName = "product_table")
public class Product extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
   // @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String productName;

    @ColumnInfo(name = "description")
    private  String productDescription;

    @ColumnInfo(name = "regular_price")
    private  String regularPrice;

    @ColumnInfo(name = "sale_price")
    private  String salePrice;

    @ColumnInfo(name = "product_photo")
    private String productPhoto;

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    private byte[] productPhoto;

    @ColumnInfo(name = "colors")
    private  String colors;

    @ColumnInfo(name = "stores")
    private  String stores;

    public Product() {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.productPhoto = productPhoto;
        this.colors = colors;
        this.stores = stores;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyPropertyChanged(BR.productName);
    }

    @Bindable
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
        notifyPropertyChanged(BR.productDescription);
    }

    @Bindable
    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        notifyPropertyChanged(BR.regularPrice);
    }

    @Bindable
    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
        notifyPropertyChanged(BR.salePrice);
    }

    @Bindable
    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
        notifyPropertyChanged(BR.productPhoto);
    }

//    @BindingAdapter("profileImage")
//    public byte[] getProductPhoto() {
//        return productPhoto;
//    }
//
//    public void setProductPhoto(byte[] productPhoto) {
//        this.productPhoto = productPhoto;
//        notifyPropertyChanged(BR.productPhoto);
//    }

    @Bindable
    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    @Bindable
    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", regularPrice='" + regularPrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", productPhoto=" + productPhoto +
                ", colors='" + colors + '\'' +
                ", stores='" + stores + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Objects.equals(getProductName(), product.getProductName()) &&
                Objects.equals(getProductDescription(), product.getProductDescription()) &&
                Objects.equals(getRegularPrice(), product.getRegularPrice()) &&
                Objects.equals(getSalePrice(), product.getSalePrice()) &&
                Objects.equals(getProductPhoto(), product.getProductPhoto()) &&
                Objects.equals(getColors(), product.getColors()) &&
                Objects.equals(getStores(), product.getStores());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getProductName(), getProductDescription(), getRegularPrice(), getSalePrice(), getProductPhoto(), getColors(), getStores());
        //result = 31 * result + Arrays.hashCode(getProductPhoto());
        return result;
    }

    //    @Override
//    public String toString() {
//        return this.getCategoryName();
////        return "Category{" +
////                "id=" + id +
////                ", categoryName='" + categoryName + '\'' +
////                ", categoryDescription='" + categoryDescription + '\'' +
////                '}';
//    }
}

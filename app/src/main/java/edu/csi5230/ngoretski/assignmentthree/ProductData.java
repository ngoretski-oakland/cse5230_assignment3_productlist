package edu.csi5230.ngoretski.assignmentthree;

import android.graphics.Bitmap;

/**
 * Created by nathan on 17/10/17.
 */

public class ProductData {

    private String productName;
    private String productPrice;
    private Bitmap image; // easiest to just save the bitmap and pass it around

    public ProductData(String productName, String productPrice, Bitmap image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductData that = (ProductData) o;

        if (image != that.image) return false;
        if (!productName.equals(that.productName)) return false;
        return productPrice.equals(that.productPrice);

    }

    @Override
    public int hashCode() {
        int result = productName.hashCode();
        result = 31 * result + productPrice.hashCode();
        result = 31 * result + image.hashCode();
        return result;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }
}

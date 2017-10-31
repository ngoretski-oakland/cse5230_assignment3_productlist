package edu.csi5230.ngoretski.assignmentthree;

import android.app.Application;

/**
 * Created by nathan on 30/10/17.
 */

public class ProductApplication extends Application {

    private ProductData productData = null;
    private ProductDataAdapter adapter;

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public void setAdapter(ProductDataAdapter adapter) {
        this.adapter = adapter;
    }

    public ProductDataAdapter getAdapter() {
        return adapter;
    }

}

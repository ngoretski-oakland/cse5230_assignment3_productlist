package edu.csi5230.ngoretski.assignmentthree;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathan on 17/10/17.
 */

public class ProductDataAdapter extends BaseAdapter {

    private final ProductApplication application;
    private final Activity mainActivity;
    List<ProductData> data = new ArrayList<>();

    public ProductDataAdapter(ProductApplication application, Activity mainActivity) {
        this.application = application;
        this.mainActivity = mainActivity;
    }

    public void addProduct(ProductData productData) {
        data.add(productData);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.product_item_layout, null, false);

        ((TextView) itemView.findViewById(R.id.price_text_view)).setText(data.get(i).getProductPrice());
        ((TextView) itemView.findViewById(R.id.product_text_view)).setText(data.get(i).getProductName());

        ((ImageView) itemView.findViewById(R.id.image_view)).setImageBitmap(data.get(i).getImage());

        final ProductData pd = data.get(i);

        // simple edit/remove buttons using the current ProductData
        ((ImageButton) itemView.findViewById(R.id.removeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(pd);
                notifyDataSetChanged();
            }
        });

        ((ImageButton) itemView.findViewById(R.id.editButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.setProductData(pd);

                Intent intent = new Intent(v.getContext(), EditProductActivity.class);
                mainActivity.startActivity(intent);
            }
        });

        return itemView;
    }
}

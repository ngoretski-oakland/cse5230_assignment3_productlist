package edu.csi5230.ngoretski.assignmentthree;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        final ProductDataAdapter adapter = new ProductDataAdapter((ProductApplication) getApplication(), this);

        ((ProductApplication) getApplication()).setAdapter(adapter);

        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creating and editing are mostly the same thing except with adding I create a blank object
                // to append the to the end of the list then 'Edit' it
                ProductData pd = new ProductData("", "", null);

                adapter.addProduct(pd);

                ((ProductApplication) getApplication()).setProductData(pd);

                Intent i = new Intent(v.getContext(), EditProductActivity.class);
                startActivity(i);
            }
        });

        listView.setAdapter(adapter);
    }
}

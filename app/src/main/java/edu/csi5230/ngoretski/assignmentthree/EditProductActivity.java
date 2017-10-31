package edu.csi5230.ngoretski.assignmentthree;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Creating a new one every time we edit/create just for simplicity
 */
public class EditProductActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;

    EditText productName;
    EditText productPrice;

    Button editDoneButton;

    ImageView editImage;

    ProductData productData;
    private Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        productName = (EditText) findViewById(R.id.editProductName);
        productPrice = (EditText) findViewById(R.id.editProductPrice);

        editImage = (ImageView) findViewById(R.id.editImage);

        editDoneButton = (Button) findViewById(R.id.editDoneButton);

        editDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productData.setProductName(productName.getText().toString());
                productData.setProductPrice(productPrice.getText().toString());
                productData.setImage(selectedImage);

                // touch the adapter so the view refreshes
                ((ProductApplication) getApplication()).getAdapter().notifyDataSetChanged();

                finish();
            }
        });

        // setters when editing
        productData = ((ProductApplication) getApplication()).getProductData();

        productName.setText(productData.getProductName());
        productPrice.setText(productData.getProductPrice());

        // if we clicked the create product button we'll have a null bitmap
        // and we're just going to use the ic_launcher icon so you can see
        // what to click on to modify the picture
        if (productData.getImage() == null) {
            editImage.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            selectedImage = productData.getImage();
            editImage.setImageBitmap(productData.getImage());
        }

        // getting the image gallery on the phone
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                final Uri imageUri = data.getData();
                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                    editImage.setImageBitmap(selectedImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.flupper.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flupper.test.database.SqliteDatabase;

public class CreateItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    private EditText salePrice;
    private EditText regularPrice;
    private EditText productImage;
    private Button addProduct;
    private ImageView iv_backspace;
    private SqliteDatabase sqliteDatabase;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        sqliteDatabase = new SqliteDatabase(this);

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        regularPrice = (EditText) findViewById(R.id.regular_price);
        salePrice = (EditText) findViewById(R.id.sale_price);
        productImage = (EditText) findViewById(R.id.product_image);
        addProduct = (Button) findViewById(R.id.add_product);

        iv_backspace = findViewById(R.id.iv_backspace);
        iv_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getImageIntent .setType("image/*");
                startActivityForResult(getImageIntent , 1 );
            }
        });



        id = getIntent().getIntExtra("id", 0);
        if (id > 0) {
            double _regularPrice = getIntent().getDoubleExtra("regular_price", 0);
            double _salePrice = getIntent().getDoubleExtra("sale_price", 0);
            String image = getIntent().getStringExtra("image");
            name.setText(getIntent().getStringExtra("name"));
            description.setText(getIntent().getStringExtra("description"));
            salePrice.setText(String.valueOf(_salePrice));
            regularPrice.setText(String.valueOf(_regularPrice));
            productImage.setText(image);

            addProduct.setText("Update");
        }

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id != 0) {
                    if (sqliteDatabase.updateProduct(String.valueOf(id),
                        name.getText().toString(),
                        description.getText().toString(),
                        salePrice.getText().toString(),
                        regularPrice.getText().toString(),
                        "green",
                        productImage.getText().toString()
                    )) {
                        Toast.makeText(CreateItemActivity.this, "Product update successfully", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(CreateItemActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (sqliteDatabase.insertProduct(
                        name.getText().toString(),
                        description.getText().toString(),
                        salePrice.getText().toString(),
                        regularPrice.getText().toString(),
                        "green",
                        productImage.getText().toString()
                    )) {
                        Toast.makeText(CreateItemActivity.this, "Product update successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateItemActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}

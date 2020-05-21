package com.flupper.test;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flupper.test.adapter.ProductHomeAdapter;
import com.flupper.test.database.SqliteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SqliteDatabase mDatabase;
    private Button showProduct;
    private Button createProduct;
    private List<Product> productList = new ArrayList<>();
    RecyclerView rv_viewProduct;
    ProductHomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_viewProduct = (RecyclerView) findViewById(R.id.rv_viewProduct);

        mAdapter = new ProductHomeAdapter(productList,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_viewProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_viewProduct.setItemAnimator(new DefaultItemAnimator());
        rv_viewProduct.setAdapter(mAdapter);
        rv_viewProduct.setNestedScrollingEnabled(false);

        productShow();

        mDatabase = new SqliteDatabase(this);
        showProduct = (Button) findViewById(R.id.show_product);
        createProduct = (Button) findViewById(R.id.create_product);

        showProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowProductActivity.class));
            }
        });
        createProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateItemActivity.class));
            }
        });
    }

    private void productShow() {

        Product product = new Product(1,"MIDAAS","Round Neck Printed",329,294,"","#000000");
        productList.add(product);

        product = new Product(2,"Zerol","Waterproof Casual Sandals",594,454,"","");
        productList.add(product);

        product = new Product(3,"Adidas","Feranoid Clothing",1230,1154,"","");
        productList.add(product);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
}

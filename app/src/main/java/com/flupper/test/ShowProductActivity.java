package com.flupper.test;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flupper.test.adapter.ProductAdapter;
import com.flupper.test.database.SqliteDatabase;

import java.util.List;

public class ShowProductActivity extends AppCompatActivity implements EditInterface {
    private SqliteDatabase mDatabase;
    List<Product> allProducts;
    ProductAdapter mAdapter;
    private ImageView iv_backspace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        iv_backspace = findViewById(R.id.iv_backspace);
        iv_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        RecyclerView productView = (RecyclerView) findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(linearLayoutManager);
        productView.setHasFixedSize(true);


        mDatabase = new SqliteDatabase(this);
        allProducts = mDatabase.listProducts();

        productView.setVisibility(View.VISIBLE);
        mAdapter = new ProductAdapter(this, this, allProducts);
        productView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            allProducts.clear();
            allProducts = mDatabase.listProducts();
            mAdapter.notifyData(allProducts);
        }
    }

    @Override
    public void editProduct(int position) {
        Intent intent = new Intent(this, CreateItemActivity.class);
        intent.putExtra("regular_price", allProducts.get(position).getRegularPrice());
        intent.putExtra("sale_price", allProducts.get(position).getSalePrice());
        intent.putExtra("name", allProducts.get(position).getName());
        intent.putExtra("description", allProducts.get(position).getDescription());
        intent.putExtra("color", allProducts.get(position).getColor());
        intent.putExtra("id", allProducts.get(position).getId());
        intent.putExtra("image", allProducts.get(position).getProductPhoto());
        startActivityForResult(intent, 10);
    }
}

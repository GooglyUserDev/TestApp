package com.flupper.test.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.flupper.test.EditInterface;
import com.flupper.test.Product;
import com.flupper.test.R;
import com.flupper.test.database.SqliteDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<Product> listProducts;

    private SqliteDatabase mDatabase;
    EditInterface editInterface;

    public ProductAdapter(Context context, EditInterface editInterface, List<Product> listProducts) {
        this.context = context;
        this.editInterface = editInterface;
        this.listProducts = listProducts;
        mDatabase = new SqliteDatabase(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final Product singleProduct = listProducts.get(position);

        holder.name.setText(singleProduct.getName());
        holder.description.setText(singleProduct.getName());
        holder.salePrice.setText(singleProduct.getName());
        holder.regularPrice.setText(String.valueOf(singleProduct.getRegularPrice()));
        holder.salePrice.setText(String.valueOf(singleProduct.getSalePrice()));
        Picasso.with(context).load(listProducts.get(position).getProductPhoto()).placeholder(R.drawable.first_product_demo_img).into(holder.productImage);
        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editInterface.editProduct(position);
            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                mDatabase.deleteProduct(singleProduct.getId());
                notifyDataSetChanged();
                //refresh the activity page.
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public void notifyData(List<Product> productList) {
        this.listProducts = productList;
        notifyDataSetChanged();
    }
}

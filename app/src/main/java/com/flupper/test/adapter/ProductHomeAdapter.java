package com.flupper.test.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flupper.test.Product;
import com.flupper.test.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.MyViewHolder>{

    private List<Product> productList;
    private Context ctx;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;
        public TextView regularPrice;
        public TextView salePrice;
        public ImageView productImage;
        public ImageView deleteProduct;
        public ImageView editProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.product_name);
            description = (TextView) itemView.findViewById(R.id.description);
            salePrice = (TextView) itemView.findViewById(R.id.sale_price);
            regularPrice = (TextView) itemView.findViewById(R.id.regular_price);
            productImage = (ImageView) itemView.findViewById(R.id.product_image);
            editProduct = (ImageView) itemView.findViewById(R.id.edit_product);
            deleteProduct = (ImageView) itemView.findViewById(R.id.delete_product);


        }
    }
    public ProductHomeAdapter(List<Product> productList, Context ctx){

        this.productList = productList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_home_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
       // holder.salePrice.setText((int) product.getSalePrice());
        holder.regularPrice.setText(String.valueOf(product.getRegularPrice()));
        holder.salePrice.setText(String.valueOf(product.getSalePrice()));
    //Picasso.with(ctx).load(productList.get(position).getProductPhoto()).placeholder(R.drawable.first_product_demo_img).into(holder.productImage);




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

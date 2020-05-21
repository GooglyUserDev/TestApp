package com.flupper.test.adapter;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.flupper.test.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView description;
    public TextView regularPrice;
    public TextView salePrice;
    public ImageView productImage;
    public ImageView deleteProduct;
    public ImageView editProduct;

    public ProductViewHolder(View itemView) {
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

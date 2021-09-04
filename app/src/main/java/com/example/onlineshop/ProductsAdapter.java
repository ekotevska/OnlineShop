package com.example.onlineshop;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.models.Products;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<String> priceList;
   private List<String> productlist;
    private Context mContext;
    private int rowLayout;
    int images[];
    int flag = 0;

   // private Spinner spinner;
   // private static final String[] sizes = {"S", "M", "L"};
    Products product;
    String s;

    @NonNull
    @Override

    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_row, parent, false);
        return new ProductsAdapter.ViewHolder(v);
    }

   /*
    @Override
    protected void onCreate(Bundle savedInstanceState){
        spinner = (Spinner) spinner.findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(ProductsAdapter.this,
                android.R.layout.,sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                s="S";
                break;
            case 1:
                s="M";
                    break;
            case 2:
               s="L";
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
*/
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int i) {
        String product= productlist.get(i);
        String price = priceList.get(i);
        holder.price.setText(price);
        holder.img.setImageResource(images[i]);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase database = new myDatabase(v.getContext());
                database.insertProduct(price, images[i], "s");
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Product successfully added to cart", Toast.LENGTH_SHORT).show();

            }
        });

        
    }

    @Override
    public int getItemCount() {
        return productlist == null ? 0 : productlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView product, size, price;
        public ImageView img;
        public Button button, size_s, size_m, size_l;

        public ViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price);
            img = (ImageView) itemView.findViewById(R.id.img);
            button = (Button) itemView.findViewById(R.id.button_add);
            size_s=(RadioButton) itemView.findViewById(R.id.size_s);
            size_m=(RadioButton) itemView.findViewById(R.id.size_m);
            size_l=(RadioButton) itemView.findViewById(R.id.size_l);

        }
    }

    public ProductsAdapter(Context context, int[] images, List<String> price, int rowLayout) {
        this.mContext = context;
        this.images = images;
        this.priceList = price;
        this.rowLayout = rowLayout;
    }
}

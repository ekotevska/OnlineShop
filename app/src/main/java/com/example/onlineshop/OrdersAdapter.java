package com.example.onlineshop;

import android.content.Context;
import android.os.Bundle;

import com.example.onlineshop.models.Orders;
import com.example.onlineshop.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context mContext;
    private int rowLayout;
    List<Orders> ordersList;
    List<User> userList;

    @NonNull

    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_row, parent, false);
        return new OrdersAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        Orders entry = ordersList.get(position);
        User us = userList.get(0);
        holder.price.setText(entry.getPrice());
        holder.name.setText(us.getName());
        holder.address.setText(us.getAddress());
        holder.city.setText(us.getCity());
        holder.img.setImageResource(entry.getImage());
    }
    @Override
    public int getItemCount() {
        return ordersList == null ? 0 : ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  price, name, address, city;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            price = (TextView) itemView.findViewById(R.id.price);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            city = (TextView) itemView.findViewById(R.id.city);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    public OrdersAdapter(Context context, int rowLayout, List<Orders> ordersList, List<User> userList) {
        this.mContext = context;
        this.rowLayout = rowLayout;
        this.ordersList = ordersList;
        this.userList = userList;
    }
}
package com.example.prapon.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prapon.Model.Restaurant;
import com.example.prapon.R;
import com.example.prapon.RestaurantActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>{

    private List<Restaurant> restaurantList;
    Context context;

    public RestaurantListAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_restaurant, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.restaurantName.setText("Name: "+restaurant.getName());

        Picasso.get().load(restaurant.getPicUrl())
                .fit()
                .placeholder(R.drawable.loading)
                .into(holder.restaurantLogo);
        holder.restaurantDetails.setText("Details: "+restaurant.getDetails());
        holder.restaurantDetails.setText("Address: "+restaurant.getAddress());
        holder.restaurantRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantActivity.class);
                SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = gson.toJson(restaurantList.get(position));
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("restaurantObj", json);
                editor.apply();

                Activity activity = (Activity) context;
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName, restaurantDetails, restaurantAddress;
        ImageView restaurantLogo;
        RelativeLayout restaurantRl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantDetails = itemView.findViewById(R.id.restaurantDetails);
            restaurantLogo = itemView.findViewById(R.id.restaurantLogo);
            restaurantAddress = itemView.findViewById(R.id.restaurantAddress);
            restaurantRl = itemView.findViewById(R.id.restaurantRl);
        }
    }

    public void filterList(List<Restaurant> filteredList) {
        restaurantList = filteredList;
        notifyDataSetChanged();
    }
}

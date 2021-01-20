package com.example.prapon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.prapon.Adapter.RestaurantListAdapter;
import com.example.prapon.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeActivity extends AppCompatActivity {
    private List<Restaurant> restaurantList = new ArrayList<>();
    private RecyclerView restaurantRv;
    private RestaurantListAdapter restaurantListAdapter;
    private EditText searchEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        restaurantRv = findViewById(R.id.restaurantListRv);
        restaurantListAdapter = new RestaurantListAdapter(restaurantList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        restaurantRv.setLayoutManager(mLayoutManager);
        restaurantRv.setItemAnimator(new DefaultItemAnimator());
        restaurantRv.setAdapter(restaurantListAdapter);

        getRestaurant();
        searchEt = findViewById(R.id.searchEt);
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void getRestaurant() {

        //restaurantList.add(xyz);
       // restaurantListAdapter.notifyDataSetChanged();
    }

    private void filter(String text) {
        List<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant item : restaurantList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        restaurantListAdapter.filterList(filteredList);
    }
}
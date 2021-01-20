package com.example.prapon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prapon.Fragment.MenuFragment;
import com.example.prapon.Model.Restaurant;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class RestaurantActivity extends AppCompatActivity {

    public static Restaurant restaurantObj;
    private ImageView restaurantImg;
    private TextView nameTv, ratingTv, addressTv;
    public static Button proceedBtn;
    public static int itemCount = 0;

    private FrameLayout menuFrameLayout;
    private TabLayout tabLayout;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("restaurantObj", "");
        restaurantObj = gson.fromJson(json, Restaurant.class);
        nameTv = findViewById(R.id.rName);
        ratingTv = findViewById(R.id.rRating);
        addressTv = findViewById(R.id.rAddress);
        restaurantImg = findViewById(R.id.rPic);
        proceedBtn = findViewById(R.id.btnProceed);


        if(itemCount!=0){
            proceedBtn.setVisibility(View.VISIBLE);
        }else{
            proceedBtn.setVisibility(View.GONE);
        }

        setInitData();

        menuFrameLayout = (FrameLayout) findViewById(R.id.menuFlayout);
        tabLayout = (TabLayout) findViewById(R.id.menuTabL);

        for(int i=0 ;i<restaurantObj.getMenuTopic().size();i++){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(restaurantObj.getMenuTopic().get(i));
            tabLayout.addTab(tab);
        }

        fragment = new MenuFragment(restaurantObj.getMenuTopic().get(0),restaurantObj.getMenu());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.menuFlayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragment = null;
                fragment = new MenuFragment(restaurantObj.getMenuTopic().get(tab.getPosition()),restaurantObj.getMenu());

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.menuFlayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setInitData() {
        nameTv.setText(restaurantObj.getName());
        ratingTv.setText("Rating: "+restaurantObj.getRating());
        addressTv.setText("Address: "+restaurantObj.getName());
        Picasso.get().load(restaurantObj.getPicUrl())
                .fit()
                .placeholder(R.drawable.loading)
                .into(restaurantImg);
    }
}
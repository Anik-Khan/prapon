package com.example.prapon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prapon.Model.Menu;
import com.example.prapon.Model.Restaurant;
import com.example.prapon.R;
import com.example.prapon.RestaurantActivity;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{
    private List<Menu> menuList;
    Context context;

    public MenuAdapter(List<Menu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_fragment_menu, parent, false);

        return new MenuAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.nameTv.setText(menu.getName());
        holder.detailsTv.setText(menu.getDetails());
        holder.amountTv.setText(menu.getAmount()+" BDT");
        for(int i=0;i<RestaurantActivity.restaurantObj.getMenu().size();i++){
            if(RestaurantActivity.restaurantObj.getMenu().get(i).getName().equalsIgnoreCase(menu.getName())){
                if(RestaurantActivity.restaurantObj.getMenu().get(i).isSelected()){
                    holder.menuRl.setBackgroundResource(R.color.blue);
                }else {
                    holder.menuRl.setBackgroundResource(R.color.white);
                }
                break;
            }
        }
        if(menu.isSelected()){
            holder.menuRl.setBackgroundResource(R.color.blue);
        }else{
            holder.menuRl.setBackgroundResource(R.color.white);
        }
        holder.menuRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menu.isSelected()){
                    holder.menuRl.setBackgroundResource(R.color.white);
                    menu.setSelected(false);
                    RestaurantActivity.itemCount--;
                    if(RestaurantActivity.itemCount<1){
                        RestaurantActivity.proceedBtn.setVisibility(View.GONE);
                    }

                    for(int i=0;i<RestaurantActivity.restaurantObj.getMenu().size();i++){
                        if(RestaurantActivity.restaurantObj.getMenu().get(i).getName().equalsIgnoreCase(menu.getName())){
                            RestaurantActivity.restaurantObj.getMenu().get(i).setSelected(false);
                            break;
                        }
                    }

                }else {
                    holder.menuRl.setBackgroundResource(R.color.blue);
                    menu.setSelected(true);
                    RestaurantActivity.itemCount++;
                    RestaurantActivity.proceedBtn.setVisibility(View.VISIBLE);
                    for(int i=0;i<RestaurantActivity.restaurantObj.getMenu().size();i++){
                        if(RestaurantActivity.restaurantObj.getMenu().get(i).getName().equalsIgnoreCase(menu.getName())){
                            RestaurantActivity.restaurantObj.getMenu().get(i).setSelected(true);
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout menuRl;
        TextView nameTv, detailsTv, amountTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            menuRl = itemView.findViewById(R.id.menuRl);
            nameTv = itemView.findViewById(R.id.nameTv);
            detailsTv = itemView.findViewById(R.id.infoTv);
            amountTv = itemView.findViewById(R.id.amountTv);
        }
    }
}

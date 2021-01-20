package com.example.prapon.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prapon.Adapter.MenuAdapter;
import com.example.prapon.Model.Menu;

import com.example.prapon.R;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private List<Menu> menuList ;
    private List<Menu> tempList = new ArrayList<>() ;
    private RecyclerView recyclerView;
    private String flag = "";
    private MenuAdapter menuAdapter;

    public MenuFragment(String flag, List<Menu> menuList) {
        this.flag = flag;
        this.menuList = menuList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.menuRv);
        menuAdapter = new MenuAdapter(tempList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);
        addData();
        return view;
    }

    private void addData() {
        tempList.clear();
        for(Menu menu : menuList){
            if(menu.getTopicName().equalsIgnoreCase(flag)){
                tempList.add(menu);
            }
        }
        menuAdapter.notifyDataSetChanged();
    }

}

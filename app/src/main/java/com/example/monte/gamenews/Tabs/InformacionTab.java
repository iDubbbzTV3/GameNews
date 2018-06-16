package com.example.monte.gamenews.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monte.gamenews.R;
import com.example.monte.gamenews.Service;

public class InformacionTab extends Fragment{

    public Service service;
    private RecyclerView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.tab_informacion, container, false);



        return rootView;
    }
}

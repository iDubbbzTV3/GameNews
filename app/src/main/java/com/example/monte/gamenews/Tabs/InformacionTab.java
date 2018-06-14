package com.example.monte.gamenews.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monte.gamenews.APIUtils;
import com.example.monte.gamenews.Jugadores.Player;
import com.example.monte.gamenews.PlayersAdapter;
import com.example.monte.gamenews.R;
import com.example.monte.gamenews.Service;
import com.example.monte.gamenews.Usuarios.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionTab extends Fragment{

    public Service service;
    private RecyclerView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.tab_informacion, container, false);



        return rootView;
    }
}

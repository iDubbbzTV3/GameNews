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
import com.example.monte.gamenews.Jugadores.PlayersAdapter;
import com.example.monte.gamenews.R;
import com.example.monte.gamenews.Service;
import com.example.monte.gamenews.Token.Token;
import com.example.monte.gamenews.Usuarios.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugadoresTab extends Fragment {

    public Service service;
    private RecyclerView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.tab_jugadores, container, false);

        listview = getView().findViewById(R.id.recycler_jugadores);

        service = APIUtils.getAPIService();
        service.logIn("00249716", "00249716").enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if (response.isSuccessful()) {
                    service.listaPlayerPorJuego(response.body().getToken(), response.body().toString()).enqueue(new Callback<List<Player>>() {
                        @Override
                        public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                            if (response.isSuccessful()){
                                listview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                                listview.setAdapter(new PlayersAdapter(response.body()));
                                Log.d("prueba", "onResponse: " + response.body().get(0).toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Player>> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });


        return rootView;
    }
}

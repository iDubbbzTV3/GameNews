package com.example.monte.gamenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.monte.gamenews.Jugadores.Player;
import com.example.monte.gamenews.Usuarios.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public Notificaciones notificaciones;

    private RecyclerView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView);

        notificaciones = APIUtils.getAPIService();
        notificaciones.Login("00249716", "00249716").enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                /*
                if (response.isSuccessful()) {
                    notificaciones.playerNombre(response.body().getToken()).enqueue(new Callback<List<Player>>() {
                        @Override
                        public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                            if (response.isSuccessful()){
                                listview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                listview.setAdapter(new PlayersAdapter(response.body()));
                                Log.d("prueba", "onResponse: " + response.body().get(0).toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Player>> call, Throwable t) {

                        }
                    });
                }*/

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });

    }
}

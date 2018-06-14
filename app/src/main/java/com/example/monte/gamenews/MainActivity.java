package com.example.monte.gamenews;

import android.content.ClipData;
import android.support.constraint.Placeholder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.monte.gamenews.Tabs.InformacionTab;
import com.example.monte.gamenews.Tabs.JugadoresTab;
import com.example.monte.gamenews.Tabs.NoticiasTab;
import com.example.monte.gamenews.Usuarios.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public ClipData.Item item1;

    public Service service;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private RecyclerView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerid);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = findViewById(R.id.listView);

        service = APIUtils.getAPIService();
        service.Login("00249716", "00249716").enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                /*
                if (response.isSuccessful()) {
                    service.playerNombre(response.body().getToken()).enqueue(new Callback<List<Player>>() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

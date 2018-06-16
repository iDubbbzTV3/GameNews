package com.example.monte.gamenews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.monte.gamenews.Jugadores.Player;
import com.example.monte.gamenews.Jugadores.PlayersAdapter;
import com.example.monte.gamenews.Noticias.NoticiasFrag;
import com.example.monte.gamenews.Tabs.TabFragment;
import com.example.monte.gamenews.Token.LogIn;
import com.example.monte.gamenews.Token.Token;
import com.example.monte.gamenews.Usuarios.Login;
import com.example.monte.gamenews.Usuarios.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Service service;
    private String token=null;
    SharedPreferences sp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        service = APIUtils.getAPIService();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                logIn("00249716","00249716");

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        token = sp.getString("token","");
        if(token.equals("")){
            Intent i = new Intent(this,LogIn.class);
            startActivityForResult(i,1);
        }else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new NoticiasFrag().newInstance(2,"Bearer "+token));
            transaction.commit();
        }
    }

    public void logIn(String title, String body) {
        service.logIn(title, body).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    String token = response.body().getToken();
                    showResponse(token);
                    if(token!=null) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new NoticiasFrag().newInstance(2,
                                response.body().getProcessedToken()));
                        transaction.commit();
                        Log.i("MAIN", "login submitted to API." + response.body().toString());
                        getUsers(response.body());
                    }
                    else{
                        showResponse("LAS CREDENCIALES SON INVALDIDAS");
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e("MAIN", "Unable to submit login to API.");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment= new Fragment();
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            fragment=new TabFragment();
            transaction.replace(R.id.container,fragment).commit();
        } else if (id == R.id.nav_manage) {
            fragment=new TabFragment();
            transaction.replace(R.id.container,fragment).commit();
        } else if (id == R.id.nav_share) {
            fragment=new TabFragment();
            transaction.replace(R.id.container,fragment).commit();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getUsers(Token token){
        service.getAllUsers(token.getProcessedToken()).enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                showResponse(call.request().toString());
                showResponse(call.request().headers().toString());
                showResponse(t.getMessage());
            }
        });
    }

    public void showResponse(String response) {
        Log.d("MAIN_RESPONSE", "showResponse: " + response);
    }

}

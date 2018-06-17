package gamenews.vistas;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gamenews.R;
import gamenews.adapter.DrawerAdapter;
import gamenews.fragment.GamesNewsAPIService;
import gamenews.inter.GamesNewsAPIUtils;
import gamenews.fragment.Categorias;
import gamenews.fragment.FavoritosFrag;
import gamenews.fragment.ContrasenaFrag;
import gamenews.fragment.NewsFragment;
import gamenews.menu.MenuModel;
import gamenews.viewmodel.GamesNewsViewModel;

public class MainActivity extends AppCompatActivity {
    GamesNewsAPIService service;
    private GamesNewsViewModel gamesNewsViewModel;
    SharedPreferences sp;
    private String token = null;
    DrawerAdapter drawerListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private String newsTitle, gamesTitle, settingsTitle, favoritesTitle, logouTitle;
    private LiveData<List<String>> categories;
    private ArrayList<MenuModel> childModelsList;
    private Fragment fragment;
    private String changePasswordTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        service = GamesNewsAPIUtils.getAPIService(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        gamesNewsViewModel = ViewModelProviders.of(this).get(GamesNewsViewModel.class);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        TextView name = headerLayout.findViewById(R.id.name);
        name.setText(sp.getString("username", ""));
        expandableListView = findViewById(R.id.expandableListView);
        configMenuData();
        fillExplandableList();
        token = sp.getString("token", "");
        if (token.equals("")) {
            Intent i = new Intent(this, LogIn.class);
            startActivity(i);
            finish();
        }
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, NewsFragment.newInstance(2, "Bearer " + token));
            transaction.commit();
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configMenuData() {
        getTitles();
        MenuModel menuModel = new MenuModel(newsTitle, false, true); 
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel(gamesTitle, true, true); 
        headerList.add(menuModel);
        categories = gamesNewsViewModel.getNewsCategories();
        childModelsList = new ArrayList<>();
        categories.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                childModelsList.clear();
                for (String category : strings) {

                    MenuModel childModel = new MenuModel(category, false, false);
                    childModelsList.add(childModel);
                }

            }
        });

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        menuModel = new MenuModel(settingsTitle, true, true);
        headerList.add(menuModel);
        ArrayList<MenuModel> childModelsList2 = new ArrayList<>();
        childModelsList2.add(new MenuModel(logouTitle, false, true));
        childModelsList2.add(new MenuModel(changePasswordTitle, false, true));
        childList.put(menuModel, childModelsList2);

        menuModel = new MenuModel(favoritesTitle, false, true); 
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

    }

    private void fillExplandableList() {

        drawerListAdapter = new DrawerAdapter(this, headerList, childList);
        expandableListView.setAdapter(drawerListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                getTitles();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (headerList.get(groupPosition).menuName.equals(newsTitle)) {
                            fragment = NewsFragment.newInstance(2, "Bearer " + token);
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.fragment_container, fragment).commit();
                        }

                        if (headerList.get(groupPosition).menuName.equals(settingsTitle)) {
                            fragment = new ContrasenaFrag();
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.fragment_container, fragment).commit();
                        }
                        if (headerList.get(groupPosition).menuName.equals(favoritesTitle)) {
                            fragment = FavoritosFrag.newInstance(2, "Bearer " + token);
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.fragment_container, fragment).commit();
                        }
                        if (headerList.get(groupPosition).menuName.equals(logouTitle)) {
                            sp.edit().remove("token").apply();
                            Intent i = new Intent(MainActivity.this, LogIn.class);
                            startActivity(i);
                            finish();
                        }


                        onBackPressed();
                    }
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    String category = childList.get(headerList.get(groupPosition)).get(childPosition).menuName;
                    if (categories.getValue().contains(category)) {
                        FragmentManager fm = getSupportFragmentManager();
                        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                        fragment = new Fragment();
                        fragment = new Categorias().newInstance("Bearer " + token, category);
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_container, fragment).commit();
                    }

                    if (category.equals(changePasswordTitle)) {
                        fragment = new ContrasenaFrag();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_container, fragment).commit();
                    }
                    if (category.equals(logouTitle)) {
                        sp.edit().remove("token").apply();
                        Intent i = new Intent(MainActivity.this, LogIn.class);
                        startActivity(i);
                        finish();
                    }
                    onBackPressed();
                }
                return false;
            }
        });
    }

    public void getTitles() {
        newsTitle = getResources().getString(R.string.news);
        gamesTitle = getResources().getString(R.string.games);
        settingsTitle = getResources().getString(R.string.settings);
        favoritesTitle = getResources().getString(R.string.favorites);
        logouTitle = getResources().getString(R.string.logout);
        changePasswordTitle = getString(R.string.change_password);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

package com.example.monte.gamenews.Tabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.monte.gamenews.R;
import com.example.monte.gamenews.ViewPagerAdapter;

public class TabManager extends AppCompatActivity{

    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);

        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment (new InformacionTab(), "Informacion");
        adapter.AddFragment (new JugadoresTab(), "Jugadores");
        adapter.AddFragment (new NoticiasTab(), "Noticias");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }

}

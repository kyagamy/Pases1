package com.lords.pases;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.zxing.integration.android.IntentIntegrator;
import com.loopwiki.qrsacnner.R;
import com.loopwiki.qrsacnner.ScannerActivity;
import com.lords.pases.adapters.ViewPagerAdapterUser;

public class MainPases extends AppCompatActivity {

    ViewPager mainViewPager;
    ViewPagerAdapterUser mvpa;
    MenuItem prevItem = null;
    BottomNavigationView navigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai);

        //declarar elementos
        navigation = findViewById(R.id.navigation);
        this.mainViewPager = findViewById(R.id.viewPager);

        //get Data from SP
        SharedPreferences sharedPref= getSharedPreferences("myPref", Context.MODE_PRIVATE);


        String matri =sharedPref.getString("matri",null);

        //Inicializacion de variables
        mvpa = new ViewPagerAdapterUser(getSupportFragmentManager(),matri);
        //listeners
        mainViewPager.setAdapter(mvpa);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_add_soli:
                        mainViewPager.setCurrentItem(1);
                        return true;
                    case R.id.nav_my_solis:
                        mainViewPager.setCurrentItem(0);
                        return true;
                    case R.id.nav_config:
                        mainViewPager.setCurrentItem(2);
                        return true;
                    case R.id.nav_scan:
                        new IntentIntegrator(MainPases.this).setCaptureActivity(ScannerActivity.class).initiateScan();
                        mainViewPager.setCurrentItem(0);
                        return true;


                }

                return false;
            }
        });
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (prevItem != null) {
                            prevItem.setChecked(false);
                        } else {
                            navigation.getMenu().getItem(0).setChecked(false);
                        }
                        navigation.getMenu().getItem(position).setChecked(true);
                        prevItem = navigation.getMenu().getItem(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

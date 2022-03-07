package com.example.covid_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;

import com.example.covid_tracker.fragments.FragmentLogin;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String API_URL = "https://api.covid19api.com/";
    private final String API_URL_SUMMARY = "https://corona.lmao.ninja/v2/";

    private final int FRAGMENT_LOGIN = 0;
    private final int FRAGMENT_HOME = 1;
    private final int FRAGMENT_PROFILE = 2;
    private final int FRAGMENT_NEWEST_DATA = 3;
    private final int FRAGMENT_ABOUT_VIRUS = 4;
    private final int FRAGMENT_SYMPTOMS = 5;
    private final int FRAGMENT_PREVENTION = 6;

    private int mCurrentFragment = FRAGMENT_LOGIN;


    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private void bindingView() {
        //Handle binding data for navigation view
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle =
                new ActionBarDrawerToggle(this,
                        mDrawerLayout,
                        toolbar,
                        R.string.open_nav_drawer,
                        R.string.close_nav_drawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();
        replaceFragment(new FragmentLogin());
//        navigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
    }


    //Handle navigate when click option in navigation view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.navigation_home:
                if (mCurrentFragment != FRAGMENT_HOME) {
                    replaceFragment(new FragmentLogin());
                    mCurrentFragment = FRAGMENT_LOGIN;
                }
                break;
            case R.id.navigation_login:

                break;
            case R.id.navigation_profile:

                break;
            case R.id.navigation_medical_declared_data:

                break;
            case R.id.navigation_covid_data:

                break;
            case R.id.navigation_about_virus:

                break;
            case R.id.navigation_symptoms:

                break;
            case R.id.navigation_prevention:

                break;
            default:
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Handle back button for navigation
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Handle replace fragment when click option in navigation view
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }


    //    public void getDataSummary() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL_SUMMARY)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GetDataFromJson getDataFromJson = retrofit.create(GetDataFromJson.class);
//
//        Call<List<Summary>> call = getDataFromJson.getDataSummary();
//
//        call.enqueue(new Callback<List<Summary>>() {
//            @Override
//            public void onResponse(Call<List<Summary>> call, Response<List<Summary>> response) {
//                List<Summary> summary = response.body();
//                tv_cases.setText(summary.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Summary>> call, Throwable t) {
//                tv_cases.setText(t.getMessage());
//            }
//        });
//    }
//
//    public void getDataByCountryName(String countryName) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GetDataFromJson getDataFromJson = retrofit.create(GetDataFromJson.class);
//
//        Call<List<CountryData>> call = getDataFromJson.getDataByCountry(countryName);
//
//        call.enqueue(new Callback<List<CountryData>>() {
//            @Override
//            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
//                List<CountryData> listCountryDayone = response.body();
//                tv_cases.setText(listCountryDayone.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<CountryData>> call, Throwable t) {
//                tv_cases.setText(t.getMessage());
//            }
//        });
//    }

}
package com.yeumkyuseok.islandbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.mapFragmentContainer);
        if (mapFragment == null) {
            mapFragment = new MapFragment();
            fm.beginTransaction().add(R.id.mapFragmentContainer, mapFragment).commit();
        }

        SelectFragment selectFragment = (SelectFragment) fm.findFragmentById(R.id.selectFragmentContainer);
        if (selectFragment == null) {
            selectFragment = new SelectFragment();
            fm.beginTransaction().add(R.id.selectFragmentContainer, selectFragment).commit();
        }

        mapFragment.setSelector(selectFragment);
    }
}
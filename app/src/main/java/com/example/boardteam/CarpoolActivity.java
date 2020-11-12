package com.example.boardteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import net.daum.mf.map.api.MapView;


public class CarpoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard_carpool_layout);

        MapView mapView = new MapView(this); //MapView 지도띄우기

        ViewGroup mapViewContainer = findViewById(R.id.Mapview);
        mapViewContainer.addView(mapView);
    }
}

package com.sawolabs.sdkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class second extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    double lat;
    double lon;
    private GoogleMap gmap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    ImageButton button;
    EditText editText;
    TextView IP;
    TextView ip;
    TextView LOCATION;
    TextView location;
    TextView ISP;
    TextView isp;
    TextView TIME;
    TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editTextTextPersonName);
        IP = findViewById(R.id.IP);
        ip = findViewById(R.id.ip);
        LOCATION = findViewById(R.id.LOCATION);
        location = findViewById(R.id.location);
        ISP = findViewById(R.id.ISP);
        isp = findViewById(R.id.isp);
        TIME = findViewById(R.id.TIMEZONE);
        time = findViewById(R.id.timezone);

        retrofit1();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                String newIp = editText.getText().toString();
                ip.setText(newIp);
                retrofit2(newIp);
            }
        });


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    private void retrofit1() {
        getLocation service = RetrofitInstance.getRetrofitInstance().create(getLocation.class);
        Call<currentIp> call = service.getCurr("https://api.ipify.org/?format=json");
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<currentIp>() {
            @Override
            public void onResponse(Call<currentIp> call, Response<currentIp> response) {
                ip.setText(response.body().getIp());
                retrofit2(response.body().getIp());
            }

            @Override
            public void onFailure(Call<currentIp> call, Throwable t) {

            }
        });


    }

    private void retrofit2(String ip) {
        getLocation service = RetrofitInstance.getRetrofitInstance().create(getLocation.class);
        Call<Example> call = service.getIp("at_YkmsSprvGGGQvuts2VfjG7wNtxOra", ip);
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                lat = response.body().getLocation().getLat();
                lon = response.body().getLocation().getLng();
                isp.setText(response.body().getIsp());
                time.setText("GMT-" + response.body().getLocation().getTimezone());
                location.setText(response.body().getLocation().getCity() + "," + response.body().getLocation().getCountry() + " " + response.body().getLocation().getPostalCode());
                Log.i("agul", String.valueOf(lat));
                Log.i("agul", response.body().getLocation().getRegion());
                Log.i("agul", String.valueOf(lon));
                setMap(lat, lon
                );

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("akul", t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(16);

    }

    public void setMap(double lat, double lon) {
        LatLng ny = new LatLng(lat, lon);

        Marker melbourne = gmap.addMarker(new MarkerOptions().position(ny)
                .icon(getMarkerIcon("#212121")));


        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }
}
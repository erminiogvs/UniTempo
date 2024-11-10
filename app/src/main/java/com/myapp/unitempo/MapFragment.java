package com.myapp.unitempo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private ImageButton satelliteModeButton;


    private static final LatLng GUAIRA_LOCATION = new LatLng(-24.1844, -54.1384);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        satelliteModeButton = rootView.findViewById(R.id.btn_toggle_map_type);

        satelliteModeButton.setOnClickListener(v -> toggleMapType());

        return rootView;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.setMyLocationEnabled(true);

        // Marcador fixo cidade
        mMap.addMarker(new MarkerOptions().position(GUAIRA_LOCATION).title("Guairá - PR"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GUAIRA_LOCATION, 10));

        centerMapOnUserLocation();
    }

    private void toggleMapType() {
        if (mMap != null) {
            if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);  // Muda para o mapa normal
            } else {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);  // Muda para o mapa satélite
            }
        }
    }

    private void centerMapOnUserLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    public void updateMapLocation(double latitude, double longitude, String cityName) {
        LatLng newLocation = new LatLng(latitude, longitude);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(newLocation).title(cityName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom (newLocation, 15));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    centerMapOnUserLocation();
                }
            } else {
            }
        }
    }
}
package com.myapp.unitempo;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherListFragment extends Fragment {

    private FloatingActionButton qrCodeButton;
    private TextView cityTextView, tempTextView, descriptionTextView;
    private RecyclerView forecastRecyclerView;
    private ForecastAdapter forecastAdapter;
    private List<WeatherResponse.Forecast> forecastList = new ArrayList<>();

    private MapFragment mapFragment;

    private final ActivityResultLauncher<ScanOptions> qrScanLauncher = registerForActivityResult(
            new ScanContract(),
            result -> {
                if (result.getContents() != null) {
                    String cityWoeid = result.getContents();
                    fetchWeatherData(cityWoeid);
                } else {
                    Toast.makeText(getContext(), "Escaneamento cancelado", Toast.LENGTH_SHORT).show();
                }
            }
    );

    public WeatherListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_list, container, false);

        qrCodeButton = rootView.findViewById(R.id.qrCodeButton);
        cityTextView = rootView.findViewById(R.id.cityTextView);
        tempTextView = rootView.findViewById(R.id.tempTextView);
        descriptionTextView = rootView.findViewById(R.id.descriptionTextView);
        forecastRecyclerView = rootView.findViewById(R.id.forecastRecyclerView);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        forecastAdapter = new ForecastAdapter(forecastList);
        forecastRecyclerView.setAdapter(forecastAdapter);

        // Cidade padrão definida ao iniciar
        String defaultCityWoeid = "456210";
        fetchWeatherData(defaultCityWoeid);

        qrCodeButton.setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setOrientationLocked(true);
            options.setPrompt("Aponte para um QR code para ler a cidade");
            options.setBeepEnabled(true);
            options.setCaptureActivity(CaptureActivityPortrait.class); // Atividade personalizada para manter na vertical
            qrScanLauncher.launch(options);
        });

        return rootView;
    }

    private void fetchCoordinatesFromCityName(String cityName) {
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addresses = geocoder.getFromLocationName(cityName, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();
                fetchWeatherData("456210");

                if (mapFragment != null) {
                    mapFragment.updateMapLocation(latitude, longitude, cityName);
                }
            } else {
                Toast.makeText(getContext(), "Cidade não encontrada", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Erro ao obter coordenadas", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchWeatherData(String woeid) {
        WeatherApiService service = ApiClient.getClient().create(WeatherApiService.class);
        Call<WeatherResponse> call = service.getWeatherByWoeid(woeid, "58a7aefd"); // Utilize o WOEID para buscar
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    updateWeatherUI(weatherResponse);
                } else {
                    Toast.makeText(getContext(), "Erro ao obter dados do clima", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Erro ao se conectar com a API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateWeatherUI(WeatherResponse weatherResponse) {
        if (cityTextView != null && tempTextView != null && descriptionTextView != null) {
            String city = weatherResponse.getResults().getCity();
            double temperature = weatherResponse.getResults().getTemp();
            String description = weatherResponse.getResults().getDescription();

            cityTextView.setText(city);
            tempTextView.setText(String.format("%.1f°C", temperature));
            descriptionTextView.setText(description);
        } else {
            Log.e("WeatherListFragment", "Referências para os TextViews não estão configuradas corretamente!");
        }

        updateForecastList(weatherResponse.getResults().getForecast());
    }

    public void updateForecastList(List<WeatherResponse.Forecast> forecastList) {
        this.forecastList.clear();
        this.forecastList.addAll(forecastList);
        forecastAdapter.notifyDataSetChanged();
    }
}

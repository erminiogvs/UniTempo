package com.myapp.unitempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherResponse.Forecast> forecastList;

    public WeatherAdapter(List<WeatherResponse.Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherResponse.Forecast forecast = forecastList.get(position);

        holder.cityTextView.setText(forecast.getWeekday());
        holder.temperatureTextView.setText(forecast.getMin() + "°C / " +
                forecast.getMax() + "°C");
        holder.descriptionTextView.setText("Céu: " + forecast.getDescription());
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView cityTextView;
        TextView temperatureTextView;
        TextView descriptionTextView;

        public WeatherViewHolder(View itemView) {
            super(itemView);

            cityTextView = itemView.findViewById(R.id.cityTextView);
            temperatureTextView = itemView.findViewById(R.id.tempTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}

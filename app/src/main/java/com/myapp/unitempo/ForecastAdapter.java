package com.myapp.unitempo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private List<WeatherResponse.Forecast> forecastList;

    public ForecastAdapter(List<WeatherResponse.Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        WeatherResponse.Forecast forecast = forecastList.get(position);

        // Defina os valores nos TextViews
        holder.dayTextView.setText(forecast.getWeekday());
        holder.minTempTextView.setText(String.format("%.1f°C", forecast.getMin()));
        holder.maxTempTextView.setText(String.format("%.1f°C", forecast.getMax()));
        holder.descriptionTextView.setText(forecast.getDescription());

        // Obtém a condição e define o ícone adequado
        String condition = forecast.getCondition();
        Log.d("ForecastAdapter", "Condition: " + condition);

        int imageResId = getWeatherIconResId(condition);

        if (imageResId != 0) {
            holder.weatherIconImageView.setImageResource(imageResId);
        } else {
            holder.weatherIconImageView.setImageResource(R.mipmap.deafult_foreground); // Imagem padrão
        }
    }

    private int getWeatherIconResId(String condition) {
        if (condition == null) return 0;

        switch (condition.toLowerCase()) {
            case "clear_day":
                return R.mipmap.clear_day_foreground;
            case "clear_night":
                return R.mipmap.clear_night_foreground;
            case "rain":
                return R.mipmap.rain_foreground;
            case "cloud":
                return R.mipmap.cloud_foreground;
            case "storm":
                return R.mipmap.storm_foreground;
            case "snow":
                return R.mipmap.snow_foreground;
            case "hail":
                return R.mipmap.hail_foreground;
            case "fog":
                return R.mipmap.fog_foreground;
            case "cloud_night":
                return R.mipmap.cloudly_night_foreground;
            case "cloud_day":
                return R.mipmap.cloudly_day_foreground;
            default:
                return R.mipmap.deafult_foreground;  // Imagem padrão
        }
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;
        TextView minTempTextView;
        TextView maxTempTextView;
        TextView descriptionTextView;
        ImageView weatherIconImageView;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
            minTempTextView = itemView.findViewById(R.id.minTempTextView);
            maxTempTextView = itemView.findViewById(R.id.maxTempTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            weatherIconImageView = itemView.findViewById(R.id.weatherIconImageView);
        }
    }
}

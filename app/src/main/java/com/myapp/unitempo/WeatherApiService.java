package com.myapp.unitempo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather")
    Call<WeatherResponse> getWeatherByWoeid(@Query("woeid") String woeid, @Query("key") String apiKey);

}
package com.sunil.assignment.network

import com.sunil.mvvmdemo.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("data/2.5/weather")
    fun getCurrentWeather(@QueryMap map: Map<String, String>?): Call<WeatherResponse>
}
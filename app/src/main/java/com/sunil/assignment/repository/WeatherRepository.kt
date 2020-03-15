package com.sunil.assignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sunil.assignment.network.ApiClient
import com.sunil.assignment.network.ApiInterface
import com.sunil.mvvmdemo.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class WeatherRepository {
    private var apiInterface: ApiInterface? = null

    val weatherApi: MutableLiveData<WeatherResponse?>
        get() {
            val wetherResponseMutableLiveData = MutableLiveData<WeatherResponse?>()
            val map: MutableMap<String, String> = HashMap()
            map["q"] = "bangalore"
            map["appid"] = "5ad7218f2e11df834b0eaf3a33a39d2a"
            apiInterface = ApiClient.clientAuthentication!!.create(ApiInterface::class.java)
            apiInterface!!.getCurrentWeather(map).enqueue(object : Callback<WeatherResponse?> {
                override fun onResponse(call: Call<WeatherResponse?>, response: Response<WeatherResponse?>) {
                    Log.e("TAG", response.body().toString())
                    if (response.body() != null) {
                        wetherResponseMutableLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {}
            })
            return wetherResponseMutableLiveData
        }
}
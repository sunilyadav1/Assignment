package com.sunil.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunil.assignment.repository.WeatherRepository
import com.sunil.mvvmdemo.model.WeatherResponse

class WeatherViewModel : ViewModel() {
    var weather: MutableLiveData<WeatherResponse?>? = null
        private set
    private val weatherRepository: WeatherRepository
    fun init() {
        weather = weatherRepository.weatherApi
    }

    init {
        weatherRepository = WeatherRepository()
    }
}
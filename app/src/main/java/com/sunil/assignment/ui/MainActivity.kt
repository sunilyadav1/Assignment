package com.sunil.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sunil.assignment.R
import com.sunil.assignment.viewmodel.WeatherViewModel
import com.sunil.mvvmdemo.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val FIVE_SECONDS = 1000*5
    private val handler: Handler = Handler();

    private var weatherViewModel: WeatherViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        weatherViewModel!!.init()

        handler.postDelayed(object : Runnable {
            override fun run() {
                weatherViewModel!!.init() // this method will contain your almost-finished HTTP calls
                handler.postDelayed(this, FIVE_SECONDS.toLong())
            }
        }, FIVE_SECONDS.toLong())

        weatherViewModel!!.weather!!.observe(this, Observer { wetherResponse ->
            Log.e(TAG, wetherResponse.toString())
            uiRender(wetherResponse)
        })
    }

    private fun uiRender(wetherResponse: WeatherResponse?) {
        txtCurrentCity!!.setText(wetherResponse!!.name +" ["+wetherResponse.sys.country+"]")
        tvTemp_max!!.setText("Temp Max:"+wetherResponse.main.temp_max.toString()+" C")
        tvTemp_min!!.setText("Temp Min:"+wetherResponse.main.temp_min.toString()+" C")

        tvHumidity!!.setText("Humidity:"+wetherResponse.main.humidity.toString()+" %")
        tvFeels_like!!.setText("Feels Like:"+wetherResponse.main.feels_like.toString())
        tvTemp!!.setText("Temp:"+wetherResponse.main.temp.toString()+" C")
        tvPressure!!.setText("Pressure:"+wetherResponse.main.pressure.toString()+" P")
        tvLatlog!!.setText("Latitude:"+wetherResponse.coord.lat.toString()+",  Longitude:"+wetherResponse.coord.lon.toString())

    }

    companion object {
        private val TAG = MainActivity::class.java.name
    }
}

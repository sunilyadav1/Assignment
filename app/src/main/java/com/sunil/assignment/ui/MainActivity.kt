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
        tvTemp_max!!.setText(getString(R.string.temp_max)+wetherResponse.main.temp_max.toString()+" C")
        tvTemp_min!!.setText(getString(R.string.temp_min)+wetherResponse.main.temp_min.toString()+" C")

        tvHumidity!!.setText(getString(R.string.humidity)+wetherResponse.main.humidity.toString()+" %")
        tvFeels_like!!.setText(getString(R.string.feels_like)+wetherResponse.main.feels_like.toString())
        tvTemp!!.setText(getString(R.string.temp)+wetherResponse.main.temp.toString()+" C")
        tvPressure!!.setText(getString(R.string.pressure)+wetherResponse.main.pressure.toString()+" P")
        tvLatlog!!.setText(getString(R.string.latitude)+wetherResponse.coord.lat.toString()+getString(
                    R.string.logitude)+wetherResponse.coord.lon.toString())

    }

    companion object {
        private val TAG = MainActivity::class.java.name
    }
}

package tw.helloandroid.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import tw.helloandroid.ui.adapters.ForecastListAdapter
import tw.helloandroid.R
import tw.helloandroid.domain.model.ForecastList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() {
        updateUI(readDataFromRaw())
    }

    private fun readDataFromRaw(): ForecastList {
        val jsonString = resources.openRawResource(R.raw.forecast_data)
                .bufferedReader()
                .use {
                    it.readText()
                }
        return Gson().fromJson(jsonString, ForecastList::class.java)
    }

    private fun updateUI(weekForecast: ForecastList) {
        val adapter = ForecastListAdapter(weekForecast)
        forecast_list.adapter = adapter
    }
}

package tw.helloandroid.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import tw.helloandroid.ui.adapters.ForecastListAdapter
import tw.helloandroid.R
import tw.helloandroid.data.server.ForecastResult
import tw.helloandroid.data.server.ServerDataMapper
import tw.helloandroid.domain.model.ForecastList

class MainActivity : AppCompatActivity() {

    companion object {
        const val ZIP_CODE = 1234L
    }

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

        val serverResult = Gson().fromJson(jsonString, ForecastResult::class.java)
        return ServerDataMapper().convertToDomain(ZIP_CODE, serverResult)
    }

    private fun updateUI(weekForecast: ForecastList) {
        val adapter = ForecastListAdapter(weekForecast)
        forecast_list.adapter = adapter
    }
}

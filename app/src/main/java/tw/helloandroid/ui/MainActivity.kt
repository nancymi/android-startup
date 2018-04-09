package tw.helloandroid.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import tw.helloandroid.ui.adapters.ForecastListAdapter
import tw.helloandroid.R
import tw.helloandroid.data.server.ForecastResult
import tw.helloandroid.data.server.ServerDataMapper
import tw.helloandroid.domain.commands.RequestForecastCommand
import tw.helloandroid.domain.model.ForecastList

class MainActivity : AppCompatActivity() {

    private val zipCode: Long = DEFAULT_ZIP

    companion object {
        const val DEFAULT_ZIP = 94043L
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
        async(UI) {
            val result = bg { RequestForecastCommand(zipCode).execute()}
            updateUI(result.await())
        }
    }

    private fun readDataFromRaw(): ForecastList {
        val jsonString = resources.openRawResource(R.raw.forecast_data)
                .bufferedReader()
                .use {
                    it.readText()
                }

        val serverResult = Gson().fromJson(jsonString, ForecastResult::class.java)
        return ServerDataMapper().convertToDomain(zipCode, serverResult)
    }

    private fun updateUI(weekForecast: ForecastList) {
        val adapter = ForecastListAdapter(weekForecast)
        forecast_list.adapter = adapter
    }
}

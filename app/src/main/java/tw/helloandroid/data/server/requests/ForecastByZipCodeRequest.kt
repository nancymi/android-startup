package tw.helloandroid.data.server.requests

import com.google.gson.Gson
import tw.helloandroid.data.server.ForecastResult
import tw.helloandroid.data.server.requests.DataRequest.Companion.COMPLETE_URL
import java.net.URL

class ForecastByZipCodeRequest(private val zipCode: Long, val gson: Gson = Gson()): DataRequest {

    override fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}

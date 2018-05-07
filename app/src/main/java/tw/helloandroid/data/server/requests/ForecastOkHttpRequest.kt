package tw.helloandroid.data.server.requests

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import tw.helloandroid.data.server.ForecastResult

class ForecastOkHttpRequest(private val zipCode: Long, private val gson: Gson = Gson()): DataRequest {
    companion object {
        private val client = OkHttpClient()
    }
    override fun execute(): ForecastResult? {
        val request = Request.Builder().url("${DataRequest.COMPLETE_URL}$zipCode").build()
        val response = client.newCall(request).execute()
        return gson.fromJson(response.body()?.string(), ForecastResult::class.java)
    }
}

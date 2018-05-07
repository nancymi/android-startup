package tw.helloandroid.data.server.requests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.helloandroid.data.server.ForecastResult

class ForecastRetrofitRequest(private val zipCode: Long): DataRequest {
    companion object {
        private val retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    override fun execute(): ForecastResult? {
        val forecastApi = retrofit.create(ForecastApi::class.java)
        val result = forecastApi.getForecastResult(zipCode)
        return result.execute().body()
    }

}

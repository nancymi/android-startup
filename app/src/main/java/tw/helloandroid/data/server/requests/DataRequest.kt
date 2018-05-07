package tw.helloandroid.data.server.requests

import tw.helloandroid.data.server.ForecastResult

interface DataRequest {
    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }
    fun execute(): ForecastResult
}

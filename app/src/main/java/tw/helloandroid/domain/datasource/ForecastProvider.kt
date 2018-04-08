package tw.helloandroid.domain.datasource

import tw.helloandroid.data.server.ForecastServer
import tw.helloandroid.domain.model.ForecastList
import tw.helloandroid.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = SOURCE) {
    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCE by lazy { listOf(ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private inline fun <T : Any> requestToSources(result: (ForecastDataSource) -> T?): T = sources.firstResult(result)
}

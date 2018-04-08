package tw.helloandroid.domain.datasource

import tw.helloandroid.data.server.ForecastServer

class ForecastProvider(private val sources: List<ForecastDataSource> = SOURCE) {
    companion object {
        val SOURCE by lazy { listOf(ForecastServer()) }
    }
}

package tw.helloandroid.domain.commands

import tw.helloandroid.domain.datasource.ForecastProvider
import tw.helloandroid.domain.model.ForecastList

class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<ForecastList> {
    companion object {
        const val DAYS = 7
    }
    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}

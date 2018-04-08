package tw.helloandroid.domain.datasource

import tw.helloandroid.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}

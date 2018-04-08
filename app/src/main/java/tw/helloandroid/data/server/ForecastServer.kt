package tw.helloandroid.data.server

import tw.helloandroid.domain.datasource.ForecastDataSource
import tw.helloandroid.domain.model.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        return dataMapper.convertToDomain(zipCode, result)
    }
}

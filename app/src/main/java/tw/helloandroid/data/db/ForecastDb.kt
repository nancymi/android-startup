package tw.helloandroid.data.db

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select
import tw.helloandroid.data.server.requests.DataRequest
import tw.helloandroid.domain.datasource.ForecastDataSource
import tw.helloandroid.domain.model.ForecastList

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        var result: ForecastList? = null
        forecastDbHelper.use {
            val dailyRequest = "${DayForecastTable.ID} = ? AND ${DayForecastTable.DATE} >= ?"
            val dailyForecast = select(DayForecastTable.NAME)
                    .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                    .parseList(object : MapRowParser<DayForecast> {
                        override fun parseRow(columns: Map<String, Any?>): DayForecast {
                            return DayForecast(HashMap(columns))
                        }
                    })
            val city = select(CityForecastTable.NAME)
                    .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                    .parseOpt(object : MapRowParser<CityForecast> {
                        override fun parseRow(columns: Map<String, Any?>): CityForecast {
                            return CityForecast(HashMap(columns), dailyForecast)
                        }
                    })
            city?.apply {
                result = dataMapper.convertToDomain()
            }
        }
        return result
    }
}

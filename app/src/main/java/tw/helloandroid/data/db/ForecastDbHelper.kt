package tw.helloandroid.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createIndex
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.dropTable
import tw.helloandroid.ui.App

class ForecastDbHelper(ctx: Context = App.instance) :
        ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {
    companion object {
        const val DB_NAME = "forecast.db"
        const val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //fun SQLiteDatabase.createTable(tableName: String,
        // ifNotExists: Boolean = false,
        // vararg columns: Pair<String, SqlType>) {
        db?.run {
            createTable(CityForecastTable.NAME, true,
                    CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                    CityForecastTable.CITY to TEXT,
                    CityForecastTable.COUNTRY to TEXT)

            createTable(DayForecastTable.NAME, true,
                    DayForecastTable.ID to INTEGER + PRIMARY_KEY,
                    DayForecastTable.DATE to INTEGER,
                    DayForecastTable.DESCRIPTION to TEXT,
                    DayForecastTable.HIGH to INTEGER,
                    DayForecastTable.LOW to INTEGER,
                    DayForecastTable.ICON_URL to TEXT,
                    DayForecastTable.CITY_ID to INTEGER)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            dropTable(CityForecastTable.NAME, true)
            dropTable(DayForecastTable.NAME, true)
            onCreate(this)
        }
    }
}

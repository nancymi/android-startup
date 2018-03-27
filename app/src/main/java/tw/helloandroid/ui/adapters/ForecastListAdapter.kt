package tw.helloandroid.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import tw.helloandroid.domain.model.Forecast
import tw.helloandroid.domain.model.ForecastList

class ForecastListAdapter(weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(view: View, private val click: (Forecast) -> Unit): RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) {

        }
    }

}

package tw.helloandroid.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import tw.helloandroid.R
import tw.helloandroid.domain.model.Forecast
import tw.helloandroid.domain.model.ForecastList
import tw.helloandroid.extensions.toDateString

class ForecastListAdapter(val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                itemView.let {
                    Picasso.get().load(iconUrl).into(it.icon)
                    it.date.text = date.toDateString()
                    it.description.text = description
                    it.maxTemperature.text = "${high}º"
                    it.minTemperature.text = "${low}º"
                }
            }
        }
    }
}

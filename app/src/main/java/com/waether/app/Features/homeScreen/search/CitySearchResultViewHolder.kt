package com.waether.app.Features.homeScreen.search

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.entities.City
import com.waether.app.R
import java.io.Serializable

const val ACTION_SHOW_CITY_BUTTON_CLICKED = "ACTION_SHOW_CITY_BUTTON_CLICKED"
const val EXTRA_CITY = "EXTRA_CITY"

class CitySearchResultViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val cityName by lazy { view.findViewById<TextView>(R.id.city_result_name_text_view) }
    val showButton by lazy { view.findViewById<Button>(R.id.show_city_weather_button) }

    fun bind(city: City) {
        cityName.text = city.name ?: ""
        showButton.setOnClickListener {
            Intent(ACTION_SHOW_CITY_BUTTON_CLICKED)
                .putExtra(EXTRA_CITY, city as Serializable)
                .also { view.context.sendBroadcast(it) }
        }

    }
}


class CitySearchResultAdapter(
    lifecycleOwner: LifecycleOwner,
    private val citiesMutableLiveData: MutableLiveData<List<City>>
) : RecyclerView.Adapter<CitySearchResultViewHolder>() {

    init {
        citiesMutableLiveData.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CitySearchResultViewHolder {
        return LayoutInflater
            .from(p0.context).inflate(R.layout.city_search_result, p0, false)
            .let { CitySearchResultViewHolder(it) }
    }

    override fun getItemCount(): Int {
        return citiesMutableLiveData.value!!.size
    }

    override fun onBindViewHolder(p0: CitySearchResultViewHolder, position: Int) {
        p0.bind(citiesMutableLiveData.value!![position])
    }

}
package com.waether.app.Features.homeScreen.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.waether.app.R
import com.waether.app.core.ContentViewId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragmet_home.*
import java.io.Serializable
import java.util.concurrent.TimeUnit

@ContentViewId(R.layout.activity_home)
class HomeScreen : AppCompatActivity()


class HomeFragment : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    private val disposables = CompositeDisposable()


    private val showButtonReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.showCityForecast.onNext(intent!!.getSerializableExtra(EXTRA_CITY))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmet_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchProgress.observe(this, Observer {
            progressBar.visibility = if (it!!) View.VISIBLE else View.GONE
        })

        viewModel.citiesResult.observe(this, Observer {
            Toast.makeText(activity, "result size = ${it?.size}", Toast.LENGTH_LONG).show()
        })

        viewModel.showCityForecast.debounce (500,TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{startForecastScreen(it)}
            .also { disposables.add(it)   }

            view.findViewById<Button>(R.id.search_button).setOnClickListener {
            viewModel.onSearchButtonClicked(search_editText.text?.toString())
        }

        results_recycler_view.layoutManager = LinearLayoutManager(context)
        results_recycler_view.adapter = CitySearchResultAdapter(this, viewModel.citiesResult)

       activity!!.registerReceiver(showButtonReceiver, IntentFilter(ACTION_SHOW_CITY_BUTTON_CLICKED))
    }
    private fun startForecastScreen(citySerializable: Serializable) {
        Intent(activity, ForecastActivity::class.java)
            .putExtra(EXTRA_CITY, citySerializable)
            .also { startActivity(it) }
    }
    override fun onDestroy() {
        activity!!.unregisterReceiver(showButtonReceiver)
        disposables.dispose()
        super.onDestroy()
    }
}


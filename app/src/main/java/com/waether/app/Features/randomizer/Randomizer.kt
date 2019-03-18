package com.waether.app.Features.randomizer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.waether.app.R
import kotlinx.android.synthetic.main.activity_randomized.*

class Randomizer : FragmentActivity() {
    override fun onCreate(
        savedInstanceState:
        Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randomized)
        val viewModel = ViewModelProviders.of(this).get(RandomizerViewModel::class.java)

        viewModel.numberLiveData.observe(this, Observer { randomised_text_view.text = it.toString() })
    }


}
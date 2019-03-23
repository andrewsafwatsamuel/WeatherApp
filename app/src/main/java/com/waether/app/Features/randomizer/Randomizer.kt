package com.waether.app.Features.randomizer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.waether.app.R
import kotlinx.android.synthetic.main.activity_randomized.*

const val ACTION_BUTTON_CLICK="ACTION_BUTTON_CLICK"

class Randomizer : AppCompatActivity() {

    val Receiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action== ACTION_BUTTON_CLICK){
                Toast.makeText(context,"hahahahah",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreate(
        savedInstanceState:
        Bundle?
    ) {

        registerReceiver(Receiver, IntentFilter(ACTION_BUTTON_CLICK))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randomized)
        val viewModel = ViewModelProviders.of(this).get(RandomizerViewModel::class.java)

        viewModel.numberLiveData.observe(this, Observer { randomised_text_view.text = it.toString()
            increment_button.setOnClickListener {
                viewModel.incrementNumber()
                this.sendBroadcast(Intent(ACTION_BUTTON_CLICK))
            }

        })

    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(Receiver)
    }
}
package com.example.adsdktest

import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AdClass(
    private val scope: CoroutineScope

) {
    private var timerJob: Job? =  null
    private val lastTime by lazy { System.currentTimeMillis() }


    fun startTimer(itemView: View) {
        if(timerJob != null) return
        timerJob = scope.launch {
            while (isActive) {
                val tvAd = itemView.findViewById<TextView>(R.id.tv_ad)
                Log.d("Poman", "startTimer ${tvAd.text} ${System.currentTimeMillis() - lastTime}")
                delay(500L)
            }
        }
    }

    fun setView(itemView: View, bindingAdapterPosition: Int) {
        val tvAd = itemView.findViewById<TextView>(R.id.tv_ad)
        tvAd.text = "I'm Ad. id = $bindingAdapterPosition"

        startTimer(itemView)
    }

    fun cancel() {
        timerJob?.cancel()
        timerJob = null
    }

}
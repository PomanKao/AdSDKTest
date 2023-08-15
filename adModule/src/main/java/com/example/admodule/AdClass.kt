package com.example.admodule

import android.util.Log
import android.view.View
import android.view.ViewGroup
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
    private var tvAd: TextView? = null


    fun startTimer() {
        if(timerJob != null) return
        timerJob = scope.launch {
            while (isActive) {
                Log.d("Poman", "startTimer ${tvAd?.text} ${System.currentTimeMillis() - lastTime}")
                delay(500L)
            }
        }
    }

    fun setView(itemView: View, bindingAdapterPosition: Int) {
        val rootView = (itemView.rootView as ViewGroup)
        val view = rootView.getChildAt(0)
        if (view !is TextView) {
            tvAd = TextView(itemView.context)
            rootView.addView(tvAd)
        } else {
            tvAd = view
        }
        tvAd?.text = "I'm Ad. id = $bindingAdapterPosition"

        startTimer()
    }

    fun cancel() {
        timerJob?.cancel()
        timerJob = null
    }

}
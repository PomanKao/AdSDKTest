package com.example.admodule

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdViewHolder(itemView: View) : ViewHolder(itemView) {

    private lateinit var adClass: AdClass

    fun startTimer() {
        adClass.startTimer()
    }

    fun setAdClass(adClass: AdClass, position: Int) {
        this.adClass = adClass
        adClass.setView(itemView, position)
    }

    fun cancel() {
        adClass.cancel()
    }
}
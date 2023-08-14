package com.example.adsdktest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.coroutineScope

class AdViewHolder(itemView: View) : ViewHolder(itemView) {

    private lateinit var adClass: AdClass

    fun startTimer(itemView: View) {
        adClass.startTimer(itemView)
    }

    fun setAdClass(adClass: AdClass) {
        this.adClass = adClass
        adClass.setView(itemView, bindingAdapterPosition)
    }

    fun cancel() {
        adClass.cancel()
    }
}
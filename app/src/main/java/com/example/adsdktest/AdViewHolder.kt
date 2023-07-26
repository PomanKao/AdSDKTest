package com.example.adsdktest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdViewHolder(itemView: View) : ViewHolder(itemView) {

    fun setAdClass(adClass: AdClass) {
        adClass.setView(itemView, bindingAdapterPosition)
    }

    private var tvAd: TextView

    init {
        tvAd = itemView.findViewById(R.id.tv_ad)
    }
}
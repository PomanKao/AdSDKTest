package com.example.adsdktest

import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdClass(private val recyclerView: RecyclerView?) {

    private val adRect = Rect()

    fun setView(itemView: View, bindingAdapterPosition: Int) {
        val tvAd = itemView.findViewById<TextView>(R.id.tv_ad)
        tvAd.text = "I'm Ad. id = $bindingAdapterPosition"

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                itemView.getGlobalVisibleRect(adRect)

                val screenHeight = Resources.getSystem().displayMetrics.heightPixels
                Log.d("Poman", "$bindingAdapterPosition screenHeight $screenHeight")
                Log.d("Poman", "$bindingAdapterPosition top ${adRect.top}, bottom ${adRect.bottom}")

                Log.d("Poman", "$bindingAdapterPosition is on screen ${adRect.top > 0 && adRect.bottom < screenHeight}")
            }
        })
    }

}
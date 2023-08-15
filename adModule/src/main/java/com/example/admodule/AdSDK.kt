package com.example.admodule

import android.graphics.Rect
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

class AdSDK private constructor(){

    companion object {
        @Volatile private var INSTANCE: AdSDK? = null

        fun getInstance(recyclerView: RecyclerView): AdSDK =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildAdSDK(recyclerView).also { INSTANCE = it }
            }

        private fun buildAdSDK(recyclerView: RecyclerView): AdSDK {
            this.recyclerView = recyclerView
            return AdSDK()
        }

        private lateinit var recyclerView: RecyclerView
    }

    init {
        recyclerView.postDelayed({
            val screenRect = Rect()
            recyclerView.getDrawingRect(screenRect)
            recyclerView.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (recyclerView.layoutManager is LinearLayoutManager) {
                        val manager = (recyclerView.layoutManager as LinearLayoutManager)
                        val visibleCount = manager.childCount
                        for (position in 0 until visibleCount) {
                            val itemView = manager.getChildAt(position)
                            itemView?.let {
                                val viewHolder = recyclerView.getChildViewHolder(itemView)
                                if (viewHolder is AdViewHolder) {
                                    val halfHeight = (itemView.bottom - itemView.top) / 2 + itemView.top

                                    val isShowTopHalf =
                                        halfHeight > screenRect.top && itemView.bottom < screenRect.bottom
                                    val isShowBottomHalf =
                                        itemView.top > screenRect.top && halfHeight < screenRect.bottom
                                    if (isShowTopHalf || isShowBottomHalf) {
                                        viewHolder.startTimer()
                                    } else {
                                        viewHolder.cancel()
                                    }
                                }
                            }
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }, 50)
    }

    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val adQueue: Queue<AdClass> = LinkedBlockingQueue(1)
    private var delta = 0

    fun setAdDelta(delta: Int) {
        this.delta = delta
    }

    fun getAdDelta(): Int {
        return delta
    }

    fun getAdClass(): AdClass {
        adQueue.offer(AdClass(scope))
        return adQueue.poll()!!
    }

}
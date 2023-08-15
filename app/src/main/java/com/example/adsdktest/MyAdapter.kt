package com.example.adsdktest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.admodule.AdClass
import com.example.admodule.AdViewHolder

class MyAdapter : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private const val IS_AD = 0
        private const val NOT_Ad = 1
    }

    private val objects = ArrayList<Any>()

    fun setObject(data: java.util.ArrayList<Any>) {
        val size = objects.size
        objects.clear()
        notifyItemRangeRemoved(0, size)
        objects.addAll(data)
        notifyItemRangeInserted(0, objects.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == IS_AD) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.my_ad_card_view, parent, false)
            AdViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_item_card_view, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (objects[position] is AdClass) {
            IS_AD
        } else {
            NOT_Ad
        }
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == IS_AD) {
            val adv = holder as AdViewHolder
            val adClass = objects[position] as AdClass
            adv.setAdClass(adClass, position)
        } else {
            val itemClass = objects[position] as ItemClass
            val ivh = holder as ItemViewHolder
            ivh.imageView?.setImageResource(itemClass.img)
            ivh.name?.text = itemClass.name
            ivh.email?.text = itemClass.email
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder is AdViewHolder) {
            holder.cancel()
        }
    }

}
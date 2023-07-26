package com.example.adsdktest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView? = null
    var name: TextView? = null
    var email: TextView? = null

    init {
        imageView = itemView.findViewById(R.id.id_img)
        name = itemView.findViewById(R.id.id_name)
        email = itemView.findViewById(R.id.id_email)
    }
}
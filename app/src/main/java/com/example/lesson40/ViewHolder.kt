package com.example.lesson40

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateModel(user: User, activity: MainActivity, clickedItemPosition: Int) {
        itemView.tvName.text = user.name
        itemView.tvDescriptionID.text = user.description
        itemView.imgMenuID.setOnClickListener {
            activity.menuItemClickListener(itemView.imgMenuID, clickedItemPosition, user)
        }

    }

}
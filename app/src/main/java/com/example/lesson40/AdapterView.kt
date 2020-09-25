package com.example.lesson40

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterView(private val activity: MainActivity): RecyclerView.Adapter<ViewHolder>() {

    private var models = mutableListOf<User>()

    fun setData(users: MutableList<User>) {
        models = users
        notifyDataSetChanged()
    }

    private fun findMaxNumberOfUsers() : Int {
        var maxNumberOfUser = 1
        for (i in models) if (maxNumberOfUser<i.number) maxNumberOfUser = i.number
        return maxNumberOfUser
    }

    fun addItem(currentItemPosition: Int) {
        Toast.makeText(activity, "Added USER ${findMaxNumberOfUsers()+1}", Toast.LENGTH_SHORT).show()
        models.add(currentItemPosition+1, User("Name ${findMaxNumberOfUsers()+1}", "description", findMaxNumberOfUsers()+1))
        notifyItemInserted(currentItemPosition+1)
        notifyItemRangeChanged(currentItemPosition+1, models.size)
    }

    fun deleteItem(currentItemPosition: Int) {
        models.removeAt(currentItemPosition)
        notifyItemRemoved(currentItemPosition)
        notifyItemRangeChanged(currentItemPosition, models.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateModel(models[position], activity, position)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}
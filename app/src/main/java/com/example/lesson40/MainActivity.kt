package com.example.lesson40

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter = AdapterView(this)
    var models= mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewID.adapter = adapter
        recyclerViewID.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewID.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        // Created first item
        val user: User = User("Name 1", "description", 1)
        models.add(user)
        adapter.setData(models)
    }

    fun menuItemClickListener(view: View, clickedItemPosition: Int, clickedUser: User) {
        val optionsMenu: PopupMenu = PopupMenu(this, view)
        val inflater = optionsMenu.menuInflater
        inflater.inflate(R.menu.menu_add_item, optionsMenu.menu)
        optionsMenu.show()
        optionsMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addItemID -> {
                    adapter.addItem(clickedItemPosition)
                }
                R.id.removeItemID -> {
                    val dialog = AlertDialog.Builder(this).create()
                    dialog.setTitle("USER ${clickedUser.number}")
                    dialog.setMessage("If you delete this item, you cannot restore it again!")
                    dialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ -> }
                    dialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE,"Delete") { _, _ -> adapter.deleteItem(clickedItemPosition) }
                    dialog.setCancelable(true)
                    dialog.show()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

}
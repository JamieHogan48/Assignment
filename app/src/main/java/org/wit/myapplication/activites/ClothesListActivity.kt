package org.wit.myapplication.activites


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import android.view.*
import kotlinx.android.synthetic.main.activity_clothes_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.myapplication.R
import org.wit.myapplication.main.MainApp
import org.wit.myapplication.models.ClothesModel


class ClothesListActivity : AppCompatActivity(), ClothesListener  {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes_list)
        app = application as MainApp

        val layoutManager =
            LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = ClothesAdapter(app.clothes)
        //recyclerView.adapter = ClothesAdapter(app.clothes.findAll())
        recyclerView.adapter = ClothesAdapter(app.clothes.findAll(), this)
        loadClothes();

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add-> startActivityForResult<ClothesActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClothesClick(clothing: ClothesModel) {
        startActivityForResult(intentFor<ClothesActivity>().putExtra("clothing_edit", clothing), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_clothes_list.xml
        //recyclerView.adapter?.notifyDataSetChanged()
        loadClothes();
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadClothes() {
        showClothes(app.clothes.findAll())
    }

    fun showClothes (placemarks: List<ClothesModel>) {
        recyclerView.adapter = ClothesAdapter(placemarks, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}


package org.wit.myapplication.main


import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.myapplication.models.ClothesJSONstore
import org.wit.myapplication.models.ClothesMemStore
import org.wit.myapplication.models.ClothesModel
import org.wit.myapplication.models.ClothesStore


class MainApp : Application(), AnkoLogger {

    //val clothes = ArrayList<ClothesModel>()
    lateinit var clothes: ClothesStore



    override fun onCreate() {
        super.onCreate()
        clothes = ClothesJSONstore(applicationContext)
        info("Jamies App started")


    }
}

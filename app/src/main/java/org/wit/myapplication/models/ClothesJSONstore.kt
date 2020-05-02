package org.wit.myapplication.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.myapplication.helpers.*
import java.util.*

val JSON_FILE = "clothes.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ClothesModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ClothesJSONstore : ClothesStore, AnkoLogger {

    val context: Context
    var clothes = mutableListOf<ClothesModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }
    override fun findAll(): MutableList<ClothesModel> {
        return clothes
    }

    override fun create(placemark: ClothesModel) {
        placemark.id = generateRandomId()
        clothes.add(placemark)
        serialize()
    }

    override fun update(clothing: ClothesModel) {
        val clothesList = findAll() as ArrayList<ClothesModel>
        var foundClothing: ClothesModel? = clothesList.find { p -> p.id == clothing.id }
        if (foundClothing != null) {
            foundClothing.title = clothing.title
            foundClothing.colour = clothing.colour
            foundClothing.image = clothing.image
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(clothes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        clothes = Gson().fromJson(jsonString, listType)
    }

    override fun delete(clothing: ClothesModel) {
        clothes.remove(clothing)
        serialize()
    }
}
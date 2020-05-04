package org.wit.myapplication.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ClothesMemStore : ClothesStore, AnkoLogger {
//create the arraylist
    val clothes = ArrayList<ClothesModel>()

    override fun findAll(): List<ClothesModel> {
        return clothes
    }

    //add method
    override fun create(clothing: ClothesModel) {
        clothing.id = getId()
        clothes.add(clothing)
        logAll()
    }

    //update method
    override fun update(clothing: ClothesModel) {
        var foundClothing: ClothesModel? = clothes.find { p -> p.id == clothing.id }
        if (foundClothing != null) {
            foundClothing.title = clothing.title
            foundClothing.brand = clothing.brand
            foundClothing.colour = clothing.colour
            foundClothing.lat = clothing.lat
            foundClothing.lng = clothing.lng
            foundClothing.image = clothing.image
            logAll()
        }
    }

    fun logAll() {
        clothes.forEach { info("${it}") }
    }

    //delete method
    override fun delete(clothing: ClothesModel) {
        clothes.remove(clothing)
    }
}
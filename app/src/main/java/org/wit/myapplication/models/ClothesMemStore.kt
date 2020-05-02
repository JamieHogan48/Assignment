package org.wit.myapplication.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ClothesMemStore : ClothesStore, AnkoLogger {

    val clothes = ArrayList<ClothesModel>()

    override fun findAll(): List<ClothesModel> {
        return clothes
    }
    override fun create(clothing: ClothesModel) {
        clothing.id = getId()
        clothes.add(clothing)
        logAll()
    }

    override fun update(clothing: ClothesModel) {
        var foundClothing: ClothesModel? = clothes.find { p -> p.id == clothing.id }
        if (foundClothing != null) {
            foundClothing.title = clothing.title
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

    override fun delete(clothing: ClothesModel) {
        clothes.remove(clothing)
    }
}
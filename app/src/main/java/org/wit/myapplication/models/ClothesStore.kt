package org.wit.myapplication.models


//these methods must be implamented

interface ClothesStore {
    fun findAll(): List<ClothesModel>
    fun create(clothes: ClothesModel)
    fun update(clothes: ClothesModel)
    fun delete(clothes: ClothesModel)
}
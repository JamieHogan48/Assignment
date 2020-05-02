package org.wit.myapplication.models

import org.wit.myapplication.models.ClothesModel


interface ClothesStore {
    fun findAll(): List<ClothesModel>
    fun create(clothes: ClothesModel)
    fun update(clothes: ClothesModel)
    fun delete(clothes: ClothesModel)
}
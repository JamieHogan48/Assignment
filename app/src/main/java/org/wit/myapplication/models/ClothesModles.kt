package org.wit.myapplication.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//vaules to be stored for the clothes
@Parcelize
data class ClothesModel(var id: Long = 0,
                        var title: String = "",
                        var brand: String ="",
                        var colour: String = "",
                        var image: String = "",
                        var lat : Double = 0.0,
                        var lng: Double = 0.0,
                        var zoom: Float = 0f): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
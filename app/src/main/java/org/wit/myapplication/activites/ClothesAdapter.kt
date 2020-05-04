package org.wit.myapplication.activites


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_clothes.view.*
import kotlinx.android.synthetic.main.card_clothes.view.clothesTitle
import org.wit.myapplication.R
import org.wit.myapplication.helpers.readImageFromPath
import org.wit.myapplication.models.ClothesModel


interface ClothesListener {
    fun onClothesClick(clothing: ClothesModel)
}

class ClothesAdapter constructor(private var clothes: List<ClothesModel>
                                  , private val listener: ClothesListener) : RecyclerView.Adapter<ClothesAdapter.MainHolder>() {

    //load in the card view for items created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_clothes,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val clothing = clothes[holder.adapterPosition]
        holder.bind(clothing, listener)
    }

    //get the amount of clothes stored in the app
    override fun getItemCount(): Int = clothes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(clothes: ClothesModel, listener : ClothesListener) {
            itemView.clothesTitle.text = clothes.title
            itemView.brand.text = clothes.brand
            itemView.description.text = clothes.colour
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, clothes.image))
            itemView.setOnClickListener {
                listener.onClothesClick(clothes)
            }
        }


    }
}
package org.wit.myapplication.activites


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_clothes.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.myapplication.R
import org.wit.myapplication.helpers.readImage
import org.wit.myapplication.helpers.readImageFromPath
import org.wit.myapplication.helpers.showImagePicker
import org.wit.myapplication.main.MainApp
import org.wit.myapplication.models.ClothesModel
import org.wit.myapplication.models.Location


class ClothesActivity : AppCompatActivity(), AnkoLogger {

    var clothes = ClothesModel()
    var app : MainApp? = null
    val IMAGE_REQUEST = 1
    //var edit =false;
    val LOCATION_REQUEST = 2
    //var location = Location(52.245696, -7.139102, 15f)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)
        app = application as MainApp
        info("Jamies Dreamlist App Main Activity started..")
        var edit = false;

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        //handle shop location and setting start location values (wit)
        ShopLocation.setOnClickListener {
            info ("Set Item Pressed")
            val location = Location(52.245696, -7.139102, 15f)
            if (clothes.zoom != 0f) {
                location.lat =  clothes.lat
                location.lng = clothes.lng
                location.zoom = clothes.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)

        }



        //Loading the content into the text fields
        if (intent.hasExtra("clothing_edit")) {
            edit = true
            clothes = intent.extras.getParcelable<ClothesModel>("clothing_edit")
            clothesTitle.setText(clothes.title)
            brand.setText(clothes.brand)
            colour.setText(clothes.colour)
            clothesImage.setImageBitmap(readImageFromPath(this, clothes.image))
            if (clothes.image != null) {
                chooseImage.setText(R.string.change_clothing_image)
            }
            btnAdd.setText(R.string.save_clothes)
        }

        //what does the add button do
        btnAdd.setOnClickListener() {
            clothes.title = clothesTitle.text.toString()
            clothes.brand = brand.text.toString()
            clothes.colour = colour.text.toString()
                if (clothes.title.isEmpty()) {
                    toast(R.string.enter_clothing_title)
                } else {
                    if (edit) {
                        app!!.clothes.update(clothes.copy())
                    } else {
                        app!!.clothes.create(clothes.copy())
                    }
                }
            info("add Button Pressed: $clothesTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        //handle select image button
        chooseImage.setOnClickListener {
            info ("Select image")
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_clothes, menu)
        //if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    //handle the options menu on the add screen  (delete and cancel)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app?.clothes?.delete(clothes)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    clothes.image = data.getData().toString()
                    clothesImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_clothing_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras.getParcelable<Location>("location")
                    clothes.lat = location.lat
                    clothes.lng = location.lng
                    clothes.zoom = location.zoom
                }
            }
        }
    }



}

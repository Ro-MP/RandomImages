package com.example.randomimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagePhoto: ImageView = findViewById(R.id.imagePhoto)
        var imagePhoto2: ImageView = findViewById(R.id.imagePhoto2)
        var btnGetImage: Button = findViewById(R.id.btnGetImage)

        //var model: MainActivityViewModel = MainActivityViewModel()

        var model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        var urlImage: MutableLiveData<String?>? = model.callUrlImage()
        Picasso.get().load(urlImage?.value).into(imagePhoto)

        urlImage?.observe(this, Observer {      //Si observa algun cambio se ejecuta lo que este dentro
            print("Se ejecuta si la URL tiene algun cambio")
            Picasso.get().load(it).into(imagePhoto2)
        })

        btnGetImage.setOnClickListener {
            model.randomNumbersUrl()
        }
    }
}
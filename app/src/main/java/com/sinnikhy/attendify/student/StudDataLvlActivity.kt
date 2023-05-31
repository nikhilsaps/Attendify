package com.sinnikhy.attendify.student

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sinnikhy.attendify.R

class StudDataLvlActivity : AppCompatActivity() {
    var cameraRequest = 1888
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_data_lvl)
        var name_on_page :String=  intent.getStringExtra("studName").toString();
        var nameText : TextView = findViewById(R.id.nameofthestud)
        nameText.text = name_on_page;

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)
        imageView = findViewById(R.id.imageView)

        val photoButton: ImageButton = findViewById(R.id.imageButton2)
        photoButton.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequest)

        }
        var imgebtnclassstud:ImageButton =findViewById(R.id.imageButton1)
        imgebtnclassstud.setOnClickListener {
            Toast.makeText(this ,"CAll view  btn presed ",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,StudClaListView::class.java))
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }
    }
}
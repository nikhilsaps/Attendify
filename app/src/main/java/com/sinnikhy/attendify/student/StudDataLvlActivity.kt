package com.sinnikhy.attendify.student

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
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
import com.sinnikhy.attendify.StudInfo_ID
import com.sinnikhy.attendify.admins.AdminDataLvlActivity

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
        var academiccal ="https://iul.ac.in/Academic_Calendar.aspx"
        val photoButton: ImageButton = findViewById(R.id.imageButton2)
        photoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(academiccal))
            startActivity(intent)


        }
        var imgebtnclassstud:ImageButton =findViewById(R.id.imageButton1)
        imgebtnclassstud.setOnClickListener {
            Toast.makeText(this ,"CAll view  btn presed ",Toast.LENGTH_SHORT).show()
            var intent = Intent(this,StudClaListView::class.java)
            intent.putExtra("nameofstud",name_on_page);
            startActivity(intent)
        }
        val link2 = "https://www.iul.ac.in/DepartmentsStudentZones.aspx"

        var btn_syllab :ImageButton =findViewById(R.id.imageButton3)
        btn_syllab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link2))
            startActivity(intent)
        }
        val linkClib = "https://library.iul.ac.in/"

        var btn_Clib :ImageButton =findViewById(R.id.studcapture_button)
        btn_Clib.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkClib))
            startActivity(intent)
        }

        var officailapp:ImageButton =findViewById(R.id.officailapptrans)

        officailapp.setOnClickListener {
            val packageName = "com.something.adilkhan.mytestapplication"

            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                startActivity(intent)
            } else {
                try {
                    val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                    startActivity(playStoreIntent)
                } catch (e: ActivityNotFoundException) {
                    val playStoreWebIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
                    startActivity(playStoreWebIntent)
                }
            }
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
package com.sinnikhy.attendify.student

import android.Manifest
import android.R.attr.bitmap
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.teacher.CLAModel
import com.sinnikhy.attendify.teacher.ClaDatabaseHandler
import com.sinnikhy.attendify.teacher.PClassDataBaseHandler
import com.sinnikhy.attendify.teacher.TeachClassViewAdapter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class StudClaListView : AppCompatActivity() {
    private val dataArray = ArrayList<CLAModel>()
    var nameonpage:String = ""
    var cameraRequest = 1888
    private lateinit var dialog: Dialog
    lateinit var imageView: ImageView
    var currentDate: Date = Date()
    var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    lateinit var posi: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_cla_list_view)
        var cardrecyccontainer : RecyclerView =findViewById(R.id.classrecycler_instud_view)

        nameonpage= intent.getStringExtra("nameofstud").toString()
        val spanCount = 2 // Replace with the desired number of columns in the grid
        val layoutManager = GridLayoutManager(this, spanCount)
        cardrecyccontainer.layoutManager = layoutManager

        logAllTableNames()


        /* Generate 5 random data entries
        for (i in 1..5) {
            val stuName = "Student $i"
            val stuRoll = "Roll $i"
            val teachId = "Teacher $i"

            val model = CLAModel(stuName, stuRoll, teachId)
            dataArray.add(model)
        }
        */


        val adapter = TeachClassViewAdapter(dataArray)




        adapter.setOnItemClickListener(object : TeachClassViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click here
                // Access the CLlist[position] to get the clicked item data
                clisk(position)
            }
        })


        cardrecyccontainer.adapter = adapter


    }
    fun clisk(pos:Int){
        Toast.makeText(this,"${dataArray[pos].classname} $pos", Toast.LENGTH_SHORT).show()
        posi=dataArray[pos].classname
        openPopup()

    }
    private fun logAllTableNames() {
        val dbcl = ClaDatabaseHandler(this)
        val database = dbcl.readableDatabase
        val cursor: Cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        //var txt:TextView= findViewById(R.id.class_View_teach)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val tableName = cursor.getString(0)
                if (tableName != "android_metadata" && tableName != "sqlite_sequence") {
                    Log.d("Database", "Table: $tableName")

                    val model = CLAModel(tableName)
                    dataArray.add(model)

                }
                cursor.moveToNext()



            }
        }
        cursor.close()

        database.close()
    }

    private fun openPopup() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_lay)



        val cancelButton = dialog.findViewById<Button>(R.id.Camerabtn)
        cancelButton.setOnClickListener {

           runcamera()
        }

        val submitButton = dialog.findViewById<Button>(R.id.presentbtn)
        submitButton.setOnClickListener {
            val editText = dialog.findViewById<EditText>(R.id.editText)
            val enteredText = editText.text.toString()
            Toast.makeText(this,"attedance marked for "+enteredText+ posi,Toast.LENGTH_SHORT).show()
            val dbattendHandler = PClassDataBaseHandler(this)
            dbattendHandler.insertData(posi+"atten",enteredText, "present","",nameonpage)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            var dateString = dateFormat.format(currentDate)

            val dbattendHandler = PClassDataBaseHandler(this)
            dbattendHandler.insertData(posi+"atten",dateString, "present","",nameonpage)
            Toast.makeText(this, "attendance is marked "+dateString,Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }
    }

    fun runcamera(){
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)


        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequest)

    }
    fun markattendance(){

    }
}
package com.sinnikhy.attendify

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sinnikhy.attendify.teacher.ClaDatabaseHandler
import com.sinnikhy.attendify.teacher.PClassDataBaseHandler

class ParticularClassView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_particular_class_view)
        var cnametxt: TextView = findViewById(R.id.classname_in_PClassview)
        var tablename = intent.getStringExtra("Class_Name").toString();
        cnametxt.text = tablename

        val databaseHandler = PClassDataBaseHandler(this)
        databaseHandler.createClassStudAttendTable(tablename+"atten")
        val date = "2023-05-27"
        val attend = "Present"
        val insertedId = databaseHandler.insertData(tablename+"atten",date, attend)
        val dataList = databaseHandler.readData(tablename+"atten")
        for (rowData in dataList) {
            Log.d("Datae", rowData)

        }

        var  btn_addtocl:ImageButton =findViewById(R.id.Addstudtocl)
        btn_addtocl.setOnClickListener {
            showInputboxtoaddstudent()
        }

    }

    private fun showInputboxtoaddstudent() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter The Roll number ")
        builder.setMessage("this student will be added to this class")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->
            val enteredText = input.text.toString()
            var db =DatabaseHandler(this)
            var data = db.readData()
            val dbHelper = ClaDatabaseHandler(this)
            var tablename = intent.getStringExtra("Class_Name").toString();
            if (data[0].rollno==enteredText) {

                val name = data[0].name.toString()
                val studentId = enteredText
                dbHelper.insertData(tablename, name, studentId)
            }
            val dataList = dbHelper.readData(tablename)
            for (rowData in dataList) {
                Log.d("Datawa", rowData)
                Toast.makeText(this , rowData.toString(),Toast.LENGTH_LONG).show()
            }
            dialog.dismiss()
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        builder.show()
    }

}
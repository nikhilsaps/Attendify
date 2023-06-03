package com.sinnikhy.attendify.teacher

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sinnikhy.attendify.DatabaseHandler
import com.sinnikhy.attendify.R

var Cla_Table_NAME=""
class TeachDataLvlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_teach_data_lvl)
        var add_new_class :ImageButton=findViewById(R.id.add_new_class)
        add_new_class.setOnClickListener {
            popupfornewclass()
        }
        var open_Class_View:ImageButton=findViewById(R.id.open_Class_View)
        open_Class_View.setOnClickListener {
            val intent= Intent(this,TeachClassView::class.java)
            startActivity(intent)
        }
        var btn_UNIWEB :ImageButton =findViewById(R.id.btn_1)

        val link = "https://www.iul.ac.in/" // Replace with your desired website link

        btn_UNIWEB.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }

        val link2 = "https://www.iul.ac.in/DepartmentsStudentZones.aspx"

        var btn_syllab :ImageButton =findViewById(R.id.btn_2)
        btn_syllab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link2))
            startActivity(intent)
        }





    }
    private fun logAllTableNames() {
        val dbcl = ClaDatabaseHandler(this)
        val database = dbcl.readableDatabase
        val cursor: Cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val tableName = cursor.getString(0)
                if (tableName != "android_metadata" && tableName != "sqlite_sequence") {
                    Log.d("Database", "Table: $tableName")
                }
                cursor.moveToNext()
            }
        }
        cursor.close()

        database.close()
    }


    private fun popupfornewclass() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter New Class Name  ")
        builder.setMessage("This will create new Class. you can add the Student later to this classs")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->
            val enteredText = input.text.toString()
            Toast.makeText(this ,enteredText,Toast.LENGTH_SHORT).show();
            val dbcl = ClaDatabaseHandler(this)
            val tableName = enteredText
            dbcl.createTable(tableName)
           logAllTableNames()
            dialog.dismiss()
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        builder.show()
    }

}
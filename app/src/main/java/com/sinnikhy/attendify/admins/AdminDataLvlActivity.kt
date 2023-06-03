package com.sinnikhy.attendify.admins

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sinnikhy.attendify.DatabaseHandler
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.StudInfo_ID
import com.sinnikhy.attendify.StudRegisActivity
import com.sinnikhy.attendify.datashow.StudDataShowRecyc

class AdminDataLvlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_data_lvl)
        val  regisNewStudent: ImageButton = findViewById(R.id.regisStudAdmin)
        regisNewStudent.setOnClickListener{
            startActivity(Intent(this, StudRegisActivity::class.java))
        }
        val btn_removestud:ImageButton =findViewById(R.id.removeStudadmin)
        btn_removestud.setOnClickListener{
            showInputDialogR()
        }
        val btn_studinfo :ImageButton= findViewById(R.id.showstuddata)
        btn_studinfo.setOnClickListener(){
            showInputDialog()
        }
        val allreport:ImageButton=findViewById(R.id.showallreport)
        allreport.setOnClickListener(){
            startActivity(Intent(this,StudDataShowRecyc::class.java));
        }


    }
    public fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter The Roll number ")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->
            val enteredText = input.text.toString()

            val intent = Intent(this, StudInfo_ID::class.java)
            intent.removeExtra("STRING_KEY")
            intent.putExtra("STRING_KEY", enteredText)
            startActivity(intent)

            dialog.dismiss()
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        builder.show()
    }
    private fun showInputDialogR() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter The Roll number ")
        builder.setMessage("The student with entered roll number will be removed from database")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->
            val enteredText = input.text.toString()

            var db = DatabaseHandler(this)
            db.deleteData(enteredText)
            Toast.makeText(this,"Student with roll number "+enteredText + " is removed form the database",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        builder.show()
    }
}

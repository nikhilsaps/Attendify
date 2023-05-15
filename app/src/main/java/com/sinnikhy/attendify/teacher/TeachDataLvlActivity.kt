package com.sinnikhy.attendify.teacher

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            Cla_Table_NAME=enteredText
            dialog.dismiss()
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        builder.show()
    }

}
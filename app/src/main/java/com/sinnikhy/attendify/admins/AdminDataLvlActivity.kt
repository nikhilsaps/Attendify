package com.sinnikhy.attendify.admins

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
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
        val btn_studinfo :ImageButton= findViewById(R.id.showstuddata)
        btn_studinfo.setOnClickListener(){
            showInputDialog()
        }
        val allreport:ImageButton=findViewById(R.id.showallreport)
        allreport.setOnClickListener(){
            startActivity(Intent(this,StudDataShowRecyc::class.java));
        }


    }
    private fun showInputDialog() {
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
}

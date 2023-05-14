package com.sinnikhy.attendify.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.datashow.StudDataShowRecyc

class StudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud)
        val studEnroll: EditText = findViewById(R.id.studEnrollno)
        val studpassText:EditText = findViewById(R.id.studPass)
        val studbtn: Button =findViewById(R.id.studLoginbtn)


        studbtn.setOnClickListener {
            if( studEnroll.text.toString()=="stud"&& studpassText.text.toString()=="stud"){
                startActivity(Intent(this, StudDataShowRecyc::class.java))
                finish()
            }
            else {
                Toast.makeText(this, "Wrong Input",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.sinnikhy.attendify.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sinnikhy.attendify.R

class TeachActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teach)
        val userNameText: EditText = findViewById(R.id.teachUserName)
        val passwordText:EditText = findViewById(R.id.teachPassword)
        val teachbtn: Button =findViewById(R.id.teachloginbtn)
        teachbtn.setOnClickListener {
            if( userNameText.text.toString()=="teacher"&& passwordText.text.toString()=="teacher"){
                startActivity(Intent(this, TeachDataLvlActivity::class.java))
                finish()
            }
            else {
                Toast.makeText(this, "Wrong Input",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
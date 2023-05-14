package com.sinnikhy.attendify.admins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sinnikhy.attendify.R

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val userNameText: EditText = findViewById(R.id.adminUserName)
        val passwordText: EditText = findViewById(R.id.adminPassword)
        val adminLoginbtn: Button =findViewById(R.id.adminloginbtn)
        adminLoginbtn.setOnClickListener {
            if( userNameText.text.toString()=="admin"&& passwordText.text.toString()=="admin"){
                startActivity(Intent(this, AdminDataLvlActivity::class.java))

            }
            else {
                Toast.makeText(this, "Wrong Input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
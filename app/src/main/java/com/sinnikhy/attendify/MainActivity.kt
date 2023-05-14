package com.sinnikhy.attendify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.sinnikhy.attendify.admins.AdminActivity
import com.sinnikhy.attendify.student.StudActivity
import com.sinnikhy.attendify.teacher.TeachActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTeach : Button = findViewById(R.id.btnTeacher)
        val btnStud : Button = findViewById(R.id.btnStudent)
        val btnAdmin : Button = findViewById(R.id.adminBtn)

        btnTeach.setOnClickListener {
            Toast.makeText(this, "Teacher" , Toast.LENGTH_SHORT).show()
            startActivity(Intent(this , TeachActivity::class.java))

        }
        btnStud.setOnClickListener {
            Toast.makeText(this, "Student",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this , StudActivity::class.java))

        }
        btnAdmin.setOnClickListener {
            Toast.makeText(this, "Admin",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this , AdminActivity::class.java))

        }

    }
}
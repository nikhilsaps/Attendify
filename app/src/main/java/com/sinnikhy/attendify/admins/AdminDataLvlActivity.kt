package com.sinnikhy.attendify.admins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.sinnikhy.attendify.R
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
        val allreport:ImageView=findViewById(R.id.showallreport)
        allreport.setOnClickListener(){
            startActivity(Intent(this,StudDataShowRecyc::class.java));
        }
    }
}

package com.sinnikhy.attendify.admins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.StudRegisActivity

class AdminDataLvlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_data_lvl)
        val  regisNewStudent: ImageButton = findViewById(R.id.regisStudAdmin)
        regisNewStudent.setOnClickListener{
            startActivity(Intent(this, StudRegisActivity::class.java))
        }
    }
}

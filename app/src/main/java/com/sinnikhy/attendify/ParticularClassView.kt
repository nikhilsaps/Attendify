package com.sinnikhy.attendify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ParticularClassView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_particular_class_view)
        var cnametxt:TextView =findViewById(R.id.classname_in_PClassview)
        cnametxt.text=intent.getStringExtra("Class_Name");
    }
}
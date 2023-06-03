package com.sinnikhy.attendify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class StudRegisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_regis)
        //datatype for registering
        val rollnotextfront:String ="202305"
        val nametext: TextView =findViewById(R.id.studnametext)
        val dobtext :TextView=findViewById(R.id.studdobtext)
        val coursetext:TextView=findViewById(R.id.studcoursetext)
        val semtext:TextView=findViewById(R.id.studsemtext)
        //val studimgtext:TextView
        val btnRegisStud: Button = findViewById(R.id.btnRegisStud)
        var db =DatabaseHandler(this)

        // creating login to autogenerate enrollment number with +1  increment



        btnRegisStud.setOnClickListener {
            // creating login to autogenerate enrollment number with +1  increment
            var data = db.readData()
            var rollnotext = rollnotextfront + data.size.toString()
            if (nametext.text.toString().length> 0){
                var studobj=StudDataModel(rollnotext,nametext.text.toString(),dobtext.text.toString(),coursetext.text.toString(),semtext.text.toString())
                var db =DatabaseHandler(this)
                db.insertData(studobj)
                Toast.makeText(this, "done Roll number ${rollnotext} ",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "please fill in ",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
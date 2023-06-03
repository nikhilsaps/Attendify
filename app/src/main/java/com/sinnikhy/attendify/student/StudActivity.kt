package com.sinnikhy.attendify.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sinnikhy.attendify.DatabaseHandler
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.StudInfo_ID
import com.sinnikhy.attendify.datashow.StudDataShowRecyc

class StudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud)
        val studEnroll: EditText = findViewById(R.id.studEnrollno)
        val studpassText:EditText = findViewById(R.id.studPass)
        val studbtn: Button =findViewById(R.id.studLoginbtn)

        var db = DatabaseHandler(this)
        var data = db.readData()



        studbtn.setOnClickListener {
            for (i in 0..data.size-1){
                if ( studEnroll.text.toString()==data[i].rollno.toString()&&studpassText.text.toString()==data[i].sem){
                    Toast.makeText(this,"${data[i].rollno} , ${data[i].dob},  ${data[i].name}",Toast.LENGTH_SHORT).show()
                    var intent =Intent(this ,StudDataLvlActivity::class.java)

                    intent.putExtra("studName",data[i].name)
                    startActivity(intent)

                }
            }
        }
    }
}
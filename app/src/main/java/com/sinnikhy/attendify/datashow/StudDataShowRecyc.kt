package com.sinnikhy.attendify.datashow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.DatabaseHandler
import com.sinnikhy.attendify.R

class StudDataShowRecyc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_data_show_recyc)
        var recycler : RecyclerView =findViewById(R.id.studrecyclerview)

        recycler.layoutManager = LinearLayoutManager(this)


        var db = DatabaseHandler(this)
        var data = db.readData()







        val adapter = StudRecycAdapter(data)
        recycler.adapter = adapter

    }
}
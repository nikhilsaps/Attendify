package com.sinnikhy.attendify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.datashow.ClasReportAdapter
import com.sinnikhy.attendify.datashow.StudRecycAdapter
import com.sinnikhy.attendify.teacher.PClassDataBaseHandler

class ClaReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cla_report)
        val dbattendHandler = PClassDataBaseHandler(this)

       var tablenme= intent.getStringExtra("Claname")

        var datatoshow=dbattendHandler.readData(tablenme+"atten")
        Toast.makeText(this,"${tablenme}, ${datatoshow}",Toast.LENGTH_SHORT).show()

        var recycler : RecyclerView =findViewById(R.id.reportrecycler)
        recycler.layoutManager = LinearLayoutManager(this)

      var data =dbattendHandler.readRepINDE(tablenme+"atten")


        val adapter = ClasReportAdapter(data)
        recycler.adapter = adapter
    }
}
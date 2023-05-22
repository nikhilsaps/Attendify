package com.sinnikhy.attendify.teacher

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.datashow.StudRecycAdapter
import java.text.FieldPosition

class TeachClassView : AppCompatActivity() {
    private val dataArray = ArrayList<CLAModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teach_class_view)
        var cardrecyccontainer : RecyclerView =findViewById(R.id.classrecycler_in_view)
        val spanCount = 2 // Replace with the desired number of columns in the grid
        val layoutManager = GridLayoutManager(this, spanCount)
        cardrecyccontainer.layoutManager = layoutManager

        logAllTableNames()


        /* Generate 5 random data entries
        for (i in 1..5) {
            val stuName = "Student $i"
            val stuRoll = "Roll $i"
            val teachId = "Teacher $i"

            val model = CLAModel(stuName, stuRoll, teachId)
            dataArray.add(model)
        }
        */


        val adapter = TeachClassViewAdapter(dataArray)




        adapter.setOnItemClickListener(object : TeachClassViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click here
                // Access the CLlist[position] to get the clicked item data
                clisk(position)
            }
        })


        cardrecyccontainer.adapter = adapter


    }
    fun clisk(pos:Int){
        Toast.makeText(this,"${dataArray[pos].classname} $pos",Toast.LENGTH_SHORT).show()
    }
    private fun logAllTableNames() {
        val dbcl = ClaDatabaseHandler(this)
        val database = dbcl.readableDatabase
        val cursor: Cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        //var txt:TextView= findViewById(R.id.class_View_teach)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val tableName = cursor.getString(0)
                if (tableName != "android_metadata" && tableName != "sqlite_sequence") {
                    Log.d("Database", "Table: $tableName")

                    val model = CLAModel(tableName)
                    dataArray.add(model)

                }
                cursor.moveToNext()



            }
        }
        cursor.close()

        database.close()
    }
}
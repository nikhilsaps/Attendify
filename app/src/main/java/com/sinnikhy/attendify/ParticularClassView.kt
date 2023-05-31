package com.sinnikhy.attendify

import PartClStAdapeter
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sinnikhy.attendify.teacher.ClaDatabaseHandler
import com.sinnikhy.attendify.teacher.PClassDataBaseHandler

class ParticularClassView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_particular_class_view)
        var cnametxt: TextView = findViewById(R.id.classname_in_PClassview)
        var tablename = intent.getStringExtra("Class_Name").toString();
        cnametxt.text = tablename

        val databaseHandler = PClassDataBaseHandler(this)
        databaseHandler.createClassStudAttendTable(tablename+"atten")
        val date = "2023-05-27"
        val attend = "Present"
        val insertedId = databaseHandler.insertData(tablename+"atten",date, attend)
        val dataList = databaseHandler.readData(tablename+"atten")
        for (rowData in dataList) {
            Log.d("Datae", rowData)

        }

        var  btn_addtocl:ImageButton =findViewById(R.id.Addstudtocl)
        btn_addtocl.setOnClickListener {
            showInputboxtoaddstudent()
        }

       var  stud_list_show:RecyclerView = findViewById(R.id.particular_stud_recyc_all)
        stud_list_show.layoutManager = LinearLayoutManager(this)

        val dbHelper = ClaDatabaseHandler(this)



        var data_Stud_list =dbHelper.readDataINDE(tablename)



       val adapter = PartClStAdapeter(data_Stud_list)
        adapter.setOnItemClickListener(object : PartClStAdapeter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click here
                // Access the CLlist[position] to get the clicked item data
                studclick(position)
            }
        })



        stud_list_show.adapter = adapter




    }
    fun studclick(pos:Int){
        Toast.makeText(this,"${pos}",Toast.LENGTH_SHORT).show()

    }

    private fun showInputboxtoaddstudent() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter The Roll number ")
        builder.setMessage("this student will be added to this class")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->
            val enteredText = input.text.toString()
            var db =DatabaseHandler(this)
            var data = db.readData()
            val dbHelper = ClaDatabaseHandler(this)
            var tablename = intent.getStringExtra("Class_Name").toString();
            for(i  in 0..data.size-1) {
                if (data[i].rollno == enteredText) {

                    val name = data[i].name.toString()
                    val course= data[i].course.toString()
                    val studentId = enteredText
                    dbHelper.insertData(tablename,
                        studentId,name,course)
                }
            }
            val dataList = dbHelper.readData(tablename)
            for (rowData in dataList) {
                Log.d("Datawa", rowData)
                Toast.makeText(this , rowData.toString(),Toast.LENGTH_LONG).show()
            }
            dialog.dismiss()
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        builder.show()
    }

}
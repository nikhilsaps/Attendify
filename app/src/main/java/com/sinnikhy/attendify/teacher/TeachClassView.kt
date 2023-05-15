package com.sinnikhy.attendify.teacher

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.sinnikhy.attendify.R

class TeachClassView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teach_class_view)
        var txt:TextView= findViewById(R.id.class_View_teach)
        logAllTableNames()

    }
    private fun logAllTableNames() {
        val dbcl = ClaDatabaseHandler(this)
        val database = dbcl.readableDatabase
        val cursor: Cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        var txt:TextView= findViewById(R.id.class_View_teach)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val tableName = cursor.getString(0)
                if (tableName != "android_metadata" && tableName != "sqlite_sequence") {
                    Log.d("Database", "Table: $tableName")
                    txt.append("Table: $tableName")
                }
                cursor.moveToNext()



            }
        }
        cursor.close()

        database.close()
    }
}
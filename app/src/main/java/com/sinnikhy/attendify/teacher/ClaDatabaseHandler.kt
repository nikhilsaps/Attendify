package com.sinnikhy.attendify.teacher

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sinnikhy.attendify.COL_COURSE
import com.sinnikhy.attendify.COL_DOB
import com.sinnikhy.attendify.COL_NAME
import com.sinnikhy.attendify.COL_ROLLNO
import com.sinnikhy.attendify.COL_SEM
import com.sinnikhy.attendify.PartiStudModel
import com.sinnikhy.attendify.StudDataModel
import com.sinnikhy.attendify.TABLE_NAME


class ClaDatabaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Database creation logic
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Database upgrade logic
    }

    fun createTable(tableName: String) {
        val db = writableDatabase
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $tableName (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                student_id TEXT UNIQUE,
                course TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        db.close()
    }


    fun insertData(tableName: String, student_id:String,name: String,course:String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("student_id",student_id)
        values.put("course",course)
        db.insert(tableName, null, values)
        db.close()
    }

    fun readData(tableName: String): List<String> {
        val db = readableDatabase
        val dataList = mutableListOf<String>()
        val query = "SELECT * FROM $tableName"
        val cursor: Cursor? = db.rawQuery(query, null)
        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val studentId = cursor.getString(cursor.getColumnIndex("student_id"))
                    val rowData = "ID: $id, Name: $name, Student ID: $studentId"
                    dataList.add(rowData)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        db.close()
        return dataList
    }
    fun readDataINDE(tableName:String):MutableList<PartiStudModel> {
        var list: MutableList<PartiStudModel> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $tableName"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val stud = PartiStudModel()
                stud.Stud_name = result.getString(result.getColumnIndex("name"))
                stud.Stud_Id = result.getString(result.getColumnIndex("student_id"))
                stud.course = result.getString(result.getColumnIndex("course"))

                list.add(stud)

            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }



    // Additional methods for inserting, querying, updating, and deleting data in specific tables
}

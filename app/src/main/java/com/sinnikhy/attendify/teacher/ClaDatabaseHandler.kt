package com.sinnikhy.attendify.teacher

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



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
                student_id TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        db.close()
    }

    fun createClassStudAttendTable(tableName: String) {
        val db = writableDatabase
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $tableName (
                Stud_id INTEGER PRIMARY KEY AUTOINCREMENT,
                date DATE,
                status VARCHAR(10)
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        db.close()
    }

    fun insertData(tableName: String, name: String, studentId: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("student_id", studentId)
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


    // Additional methods for inserting, querying, updating, and deleting data in specific tables
}

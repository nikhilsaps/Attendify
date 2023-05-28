package com.sinnikhy.attendify.teacher

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class PClassDataBaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "myattendatabase.db"
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
                age INTEGER
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
                presence VARCHAR(10)
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        db.close()
    }

    fun insertData(tableName: String, date: String, status: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("date", date)
        values.put("presence", status)
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
                    val date = cursor.getString(cursor.getColumnIndex("date"))
                    val status = cursor.getString(cursor.getColumnIndex("presence"))
                    val rowData = "$date - $status"
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

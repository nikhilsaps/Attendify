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
                status VARCHAR(10)
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        db.close()
    }


    // Additional methods for inserting, querying, updating, and deleting data in specific tables
}

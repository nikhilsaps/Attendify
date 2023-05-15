package com.sinnikhy.attendify.teacher

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val C_DATABASE_NAME="ClassDB"
val TABLE_NAME= Cla_Table_NAME
val ID= "id"
val STUDENT_NAME="Stu_Name"

class ClaDatabaseHandler (context: Context) : SQLiteOpenHelper(context, C_DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME + " ("


        db?.execSQL(createTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}
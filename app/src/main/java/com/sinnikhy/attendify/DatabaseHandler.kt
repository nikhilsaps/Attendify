package com.sinnikhy.attendify

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME= "MyDB"
val TABLE_NAME= "StudentDB"
val COL_NAME="name"
val COL_ROLLNO ="rollno"
val COL_DOB = "dob"
val COL_COURSE= "course"
val COL_SEM = "sem"
val COL_STUDIMG = "stdimg"
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME+ " (" +
                COL_ROLLNO +" VARCHAR(5) ," +
                COL_NAME + " VARRCHAR(64) ,"+
                COL_DOB + " VARCHAR(10),"+
                COL_COURSE + " VARCHAR(5), "+
                COL_SEM + " INTEGER,"+
                COL_STUDIMG + " VARCHAR(256)" + ")"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertData(stud:StudDataModel){
        val db =this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ROLLNO,stud.rollno)
        cv.put(COL_NAME,stud.name)
        cv.put(COL_DOB,stud.dob)
        cv.put(COL_COURSE,stud.course)
        cv.put(COL_SEM,stud.sem)



        val result=db.insert(TABLE_NAME,null,cv)
        if (result == -1.toLong()){


        }

    }
    fun readData():MutableList<StudDataModel> {
        var list: MutableList<StudDataModel> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val stud = StudDataModel()
                stud.rollno = result.getString(result.getColumnIndex(COL_ROLLNO))
                stud.name = result.getString(result.getColumnIndex(COL_NAME))
                stud.dob = result.getString(result.getColumnIndex(COL_DOB))
                stud.course = result.getString(result.getColumnIndex(COL_COURSE))
                stud.sem = result.getString(result.getColumnIndex(COL_SEM))
                list.add(stud)

            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun deleteData(rollNo: String): Int {
        val db = this.writableDatabase
        val columnRollNo = COL_ROLLNO

        val whereClause = "$columnRollNo = ?"
        val whereArgs = arrayOf(rollNo.toString())

        val deletedRowCount = db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()

        return deletedRowCount
    }
}
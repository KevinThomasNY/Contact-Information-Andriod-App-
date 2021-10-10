package com.example.finalproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val TABLE_CONTACTS="Contacts"
val ID="Number"
val NAME= "Name"
val BIRTHDAY= "Birthday"

class DataBaseManager(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 6
        const val DATABASE_NAME = "student.db"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS ${TABLE_CONTACTS} (" +
                "${ID} TEXT PRIMARY KEY, " +
                "${NAME}TEXT, " +
                "${BIRTHDAY}TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        Log.e("TAG", "onUpgrade:$p2")
        p0?.execSQL("drop table if exists $TABLE_CONTACTS")
        onCreate(p0)
    }

    fun insert(studentToInsert:Contacts)
    {
        val db = this.writableDatabase
        val insertString= "INSERT INTO $TABLE_CONTACTS " +
                "VALUES ( '${studentToInsert.contactsName}','${studentToInsert.contactsNumber}','${studentToInsert.contactsBirthday}')"
        db.execSQL(insertString)
    }

    fun selectAll():ArrayList<Contacts>
    {
        val sqlQuery = "select * from $TABLE_CONTACTS"

        val db = this.writableDatabase
        var toReturn= ArrayList<Contacts>()
        var cursor=db.rawQuery(sqlQuery, null);
        while(cursor.moveToNext())
        {
            toReturn.add(Contacts(cursor.getString(0), cursor.getString(1), cursor.getString(2)))

        }
        return toReturn
    }



}

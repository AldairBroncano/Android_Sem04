package com.example.semana4_home

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log



class SqlLite (context: Context):SQLiteOpenHelper (context,"personas.db",null,1) {

    
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE amigos " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, email TEXT)"
        db!!.execSQL(ordenCreacion)

    }



























}
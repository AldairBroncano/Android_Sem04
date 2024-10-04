package com.example.semana4_home

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log



class SqlLite (context: Context):SQLiteOpenHelper (context,"personas.db",null,1) {


    override fun onCreate(db: SQLiteDatabase?) { // se crea bd
        val ordenCreacion = "CREATE TABLE amigos " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, email TEXT)"
        db!!.execSQL(ordenCreacion)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { //Borra la tabla existente y la recrea con la nueva estructura.
        val ordenBorrado = "DROP TABLE IF EXISTS amigos"
        db!!.execSQL(ordenBorrado)
        onCreate(db)

    }



    //**añadir datos***
    fun anyadirDato(nombre: String, email: String) {
        val datos = ContentValues()

        //da valores a datos
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase // declara objeto db
        db.insert("amigos", null, datos) //inserta por metodo insert
        db.close()
    }


    fun borrarDato(id: Int) : Int {
        val args = arrayOf(id.toString())
        Log.d("borrado","Hola mundo ${args.joinToString()}") //muestra mensaje
        val db = this.writableDatabase
        val borrados = db.delete("amigos", "_id = ?", args)
        // db.execSQL("DELETE FROM amigos WHERE id = ?", args)
        db.close()
        return borrados
    }


    fun modificarDato(id: Int, nombre: String, email: String) {
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase
        db.update("amigos", datos, "_id = ?", args)
        db.close()
    }


    // Método para consultar datos

    fun consultarDatos(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM amigos", null)
    }
    // Nuevo método para buscar por nombre usando LIKE
    fun consultarPorNombre(nombre: String): Cursor {
        val db = this.readableDatabase
        // Utilizamos el comodín % para buscar cualquier nombre que contenga la cadena proporcionada
        val args = arrayOf("%$nombre%")
        return db.rawQuery("SELECT * FROM amigos WHERE nombre LIKE ?", args)
    }




    fun consultarPorNombreYEmail(nombre: String, email: String): Cursor {
        val db = this.readableDatabase

        // Consulta con dos placeholders para nombre y email
        val args = arrayOf(nombre, email)
        return db.rawQuery("SELECT * FROM amigos WHERE nombre = ? AND email = ?", args)
    }







}
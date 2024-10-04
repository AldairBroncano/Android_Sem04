package com.example.semana4_home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.semana4_home.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding // esto vincula al activty_main.xml
    lateinit var amigosDBHelper :SqlLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicio del Bindin para leer o escribir las variables del xml Activity_main
        // dar valor al binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // binding.etNombre.setText("Hola")

        setContentView(binding.root)
        ///////
        //Inicio para usar la bd
        //dar valor a amigosDBHelper

        amigosDBHelper = SqlLite(this)

        //////
        // Aqui programamos cuando hacer clic btGuardar
        binding.btGuardar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank()) {
                amigosDBHelper.anyadirDato(binding.etNombre.text.toString(),
                    binding.etEmail.text.toString())
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()
                Toast.makeText(this, "Guardado",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "No se ha podido guardar",
                    Toast.LENGTH_LONG).show()
            }
        }


        binding.btConsultar.setOnClickListener {
            //val cursor = amigosDBHelper.consultarDatos()
            val cursor = amigosDBHelper.consultarPorNombre(binding.etNombre.text.toString())
            binding.tvConsulta.text = "Listado de Personas : " + "\n"
            if (cursor.moveToFirst()) {
                do {
                    binding.tvConsulta.append(
                        cursor.getInt(0).toString() + ": "
                    )
                    binding.tvConsulta.append(
                        cursor.getString(1).toString() + ", "
                    )
                    binding.tvConsulta.append(
                        cursor.getString(2).toString() + "\n"
                    )
                } while (cursor.moveToNext())

            }
            cursor.close()
            amigosDBHelper.close()

        }


        binding.btBorrar.setOnClickListener {

            var cantidad = 0

            if (binding.etId.text.isNotBlank()) {
                cantidad = amigosDBHelper.borrarDato(
                    binding.etId.text.toString().toInt()
                )
                binding.etId.text.clear()
            } else {
                Toast.makeText(
                    this,
                    "Datos borrados: $cantidad",
                    Toast.LENGTH_LONG
                ).show()
            }

        }



        binding.btModificar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank() &&
                binding.etId.text.isNotBlank()
            ) {
                amigosDBHelper.modificarDato(
                    binding.etId.text.toString().toInt(),
                    binding.etNombre.text.toString(),
                    binding.etEmail.text.toString()
                )
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()
                binding.etId.text.clear()
                Toast.makeText(
                    this, "Modificado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Los campos no deben estar vacíos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }








    }





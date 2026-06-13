package com.example.proyectofinal

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTarea: EditText
    private lateinit var btnAgregar: Button
    private lateinit var listaTareas: ListView
    private val tareas = ArrayList<String>()

    private fun guardarTareas() { //funcion para guardar tareas

        val preferencias = getSharedPreferences("MisTareas", MODE_PRIVATE)

        val editor = preferencias.edit()

        editor.putStringSet("listaTareas", tareas.toSet())

        editor.apply()
    }

    private fun cargarTareas() { //función para cargar tareas

        val preferencias = getSharedPreferences("MisTareas", MODE_PRIVATE)

        val tareasGuardadas =
            preferencias.getStringSet("listaTareas", emptySet())

        tareas.clear()

        tareas.addAll(tareasGuardadas!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTarea = findViewById(R.id.etTarea)
        btnAgregar = findViewById(R.id.btnAgregar)
        listaTareas = findViewById(R.id.listaTareas)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tareas
        )

        listaTareas.adapter = adapter
        cargarTareas()
        adapter.notifyDataSetChanged()

        btnAgregar.setOnClickListener {

            val nuevaTarea = etTarea.text.toString()

            if (nuevaTarea.isNotEmpty()) {

                tareas.add(nuevaTarea)

                guardarTareas()

                adapter.notifyDataSetChanged()

                etTarea.text.clear()
            }
        }
        listaTareas.setOnItemClickListener { _, _, position, _ -> //position significa la posición del elemento tocado

            tareas.removeAt(position)  //elimina la tarea

            guardarTareas()

            etTarea.requestFocus() //despues de borrar las tareas vuelve a dar el foco al EditText porque borraba tareas y no se desplegaba de nuevo el teclado

            adapter.notifyDataSetChanged()
        }
    }
}
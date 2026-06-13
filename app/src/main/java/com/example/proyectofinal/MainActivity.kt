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

        btnAgregar.setOnClickListener {

            val nuevaTarea = etTarea.text.toString()

            if (nuevaTarea.isNotEmpty()) {

                tareas.add(nuevaTarea)

                adapter.notifyDataSetChanged()

                etTarea.text.clear()
            }
        }
    }
}
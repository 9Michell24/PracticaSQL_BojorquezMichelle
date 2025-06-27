package mx.edu.potros.practicasql_bojorquezmichelle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.widget.Toast
import mx.edu.potros.practicasql_bojorquezmichelle.data.AlumnoSQLHelper
import mx.edu.potros.practicasql_bojorquezmichelle.entities.Alumno

class MainActivity : AppCompatActivity() {
    private lateinit var db: AlumnoSQLHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db=AlumnoSQLHelper(this)

        val nombre = findViewById<EditText>(R.id.nombre)
        val paterno = findViewById<EditText>(R.id.paterno)
        val materno = findViewById<EditText>(R.id.materno)
        val carrera = findViewById<EditText>(R.id.carrera)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombre = nombre.text.toString().trim()
            val paterno = paterno.text.toString().trim()
            val materno = materno.text.toString().trim()
            val carrera = carrera.text.toString().trim()
            val alumno= Alumno(nombre, paterno, materno, carrera)

            db.insertAlumno(alumno)
            Toast.makeText(this, "Alumno guardado", Toast.LENGTH_SHORT).show()

            if (nombre.isEmpty() || paterno.isEmpty() || materno.isEmpty() || carrera.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val alumno = Alumno(
                    nombre = nombre,
                    paterno = paterno,
                    materno = materno,
                    carrera = carrera
                )
                Toast.makeText(this, "Alumno guardado", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Alumno guardado: $alumno")
            }
        }
    }
}
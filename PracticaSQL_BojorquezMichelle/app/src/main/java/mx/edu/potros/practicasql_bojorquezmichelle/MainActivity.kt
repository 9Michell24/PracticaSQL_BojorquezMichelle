package mx.edu.potros.practicasql_bojorquezmichelle

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.nombre)
        val etApPat = findViewById<EditText>(R.id.apPat)
        val etApMat = findViewById<EditText>(R.id.apMat)
        val etCarrera = findViewById<EditText>(R.id.carrera)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apPat = etApPat.text.toString().trim()
            val apMat = etApMat.text.toString().trim()
            val carrera = etCarrera.text.toString().trim()

            if (nombre.isEmpty() || apPat.isEmpty() || apMat.isEmpty() || carrera.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val alumno = Alumno(
                    nombre = nombre,
                    apPaterno = apPat,
                    apMaterno = apMat,
                    carrera = carrera
                )
                Toast.makeText(this, "Alumno guardado", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Alumno guardado: $alumno")
            }
        }
    }
}
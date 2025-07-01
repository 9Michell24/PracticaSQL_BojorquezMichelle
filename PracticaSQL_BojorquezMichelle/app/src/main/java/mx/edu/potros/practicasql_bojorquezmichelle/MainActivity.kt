package mx.edu.potros.practicasql_bojorquezmichelle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.potros.practicasql_bojorquezmichelle.data.AlumnoSQLHelper
import mx.edu.potros.practicasql_bojorquezmichelle.entities.Alumno
import mx.edu.potros.practicasql_bojorquezmichelle.entities.AlumnosAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var db: AlumnoSQLHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var alumnoAdapter: AlumnosAdapter
    private val listaAlumnos = mutableListOf<Alumno>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AlumnoSQLHelper(this)

        val nombreAlumno = findViewById<EditText>(R.id.nombre)
        val apPaterno = findViewById<EditText>(R.id.paterno)
        val apMaterno = findViewById<EditText>(R.id.materno)
        val carreraAlumno = findViewById<EditText>(R.id.carrera)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        recyclerView = findViewById(R.id.studentsList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar alumnos iniciales
        listaAlumnos.addAll(db.getAllAlumnos())
        alumnoAdapter = AlumnosAdapter(listaAlumnos, this)
        recyclerView.adapter = alumnoAdapter

        btnGuardar.setOnClickListener {
            val nombre = nombreAlumno.text.toString().trim()
            val paterno = apPaterno.text.toString().trim()
            val materno = apMaterno.text.toString().trim()
            val carrera = carreraAlumno.text.toString().trim()

            if (nombre.isEmpty() || paterno.isEmpty() || materno.isEmpty() || carrera.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val alumno = Alumno(nombre, paterno, materno, carrera)
                db.insertAlumno(alumno)
                Toast.makeText(this, "Alumno guardado", Toast.LENGTH_SHORT).show()

                // Actualizar lista del adapter
                val nuevaLista = db.getAllAlumnos()
                alumnoAdapter.actualizarLista(nuevaLista)

                // Limpiar campos
                nombreAlumno.text.clear()
                apPaterno.text.clear()
                apMaterno.text.clear()
                carreraAlumno.text.clear()
            }
        }
    }
}

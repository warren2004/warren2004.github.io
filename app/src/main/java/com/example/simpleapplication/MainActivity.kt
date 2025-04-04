package com.example.simpleapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleapplication.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample data
        val branches = listOf(
            Engineering(name = "Electrical Engineering", semesters = mapOf(
                1 to listOf(
                    Modules("Circuit Analysis",5),
                    Modules("Power Electronics",5),
                    Modules("Engineering Drawing",5),
                    Modules("Physics for Engineers",10),
                    Modules("Analogue Electronics",5)

                )    ,
                2 to listOf(
                    Modules("Engineering Maths",5),
                    Modules("Control Systems",10),
                    Modules("Electromagnetic Theory",5),
                    Modules("Engineering Design",10)

                ),

            )),
            Engineering("Mechanical Engineering", semesters =  mapOf(
                1 to listOf(
                    Modules("Fluid Mechanics I",3),
                    Modules("Thermodynamics",5),
                    Modules("Solid Mechanics",5),
                    Modules("Engineering Drawing",10),
                    Modules("Physics for Engineers",10),
                ),
                2 to listOf(
                    Modules("Machine Dynamics",10),
                    Modules("Heat Transfer",5),
                    Modules("Material Science",5),
                    Modules("Engineering Drawing ",5),
                    Modules("Fusion Theory",5),
                ),

            )),
            Engineering("Civil Engineering" ,semesters= mapOf(
                1 to listOf(
                    Modules("Engineering Mechanics",10),
                    Modules("Surveying",5),
                    Modules("Engineering Drawing",5),
                    Modules("Structural Engineering ",5),
                    Modules("Engineering Ethics",5),
                ),
                2 to listOf(
                    Modules("Structural Analysis",10),
                    Modules("Soil Mechanics",5),
                    Modules("Hydraulics",5),
                    Modules("Construction Management",5),
                    Modules("Transportation Dynamics",5),
                )
            )),
            Engineering("Aeronautical Engineering", semesters = mapOf(
                1 to listOf(
                    Modules("Aerodynamics",10),
                    Modules("Aircraft Structures",5),
                    Modules("Engineering Drawing",5),
                    Modules("Fluid Dynamics ",5),
                    Modules("Physics for Engineers",5),
                ),
                2 to listOf(
                    Modules("Flight Mechanics",10),
                    Modules("Propulsion Systems",5),
                    Modules("Materials Science",5),
                    Modules("Aircraft Systems",5),
                    Modules("Aviation Regulations",5),
                )
            )),




        )
        // Setup RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EngineeringAdapter(branches)
        }
    }
}
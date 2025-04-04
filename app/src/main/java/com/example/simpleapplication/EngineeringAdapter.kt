
package com.example.simpleapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EngineeringAdapter(
    private val branches: List<Engineering>
) : RecyclerView.Adapter<EngineeringAdapter.EngineeringViewHolder>() {
    private var select="Select Semester"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineeringViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_engineering, parent, false)
        return EngineeringViewHolder(view)
    }

    override fun onBindViewHolder(holder: EngineeringViewHolder, position: Int) {
        holder.bind(branches[position])
    }

    override fun getItemCount() = branches.size

    inner class EngineeringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val branchName: TextView = itemView.findViewById(R.id.branchName)
        private val semesterSpinner: Spinner = itemView.findViewById(R.id.semesterSpinner)
        private val modulesRecyclerView: RecyclerView = itemView.findViewById(R.id.modulesForMe)
        private lateinit var moduleAdapter: ModuleAdapter
        private lateinit var spinnerAdapter: ArrayAdapter<String>

        // Setup Degree and Semester selection
        fun bind(branch: Engineering) {
            branchName.text = branch.name

            // Prepare semester numbers from the Engineering object
            val semesterNumbers = branch.semesters.keys.sorted()

            // Initialize spinner adapter for semester selection
            val spinnerItems = mutableListOf<String>()
            spinnerItems.add(select) // Add the prompt at the beginning
            spinnerItems.addAll(semesterNumbers.map { "Semester $it" })

            spinnerAdapter = ArrayAdapter(
                itemView.context,
                android.R.layout.simple_spinner_item,
                spinnerItems
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            semesterSpinner.adapter = spinnerAdapter

            // Setup modules RecyclerView with an initially empty list
            moduleAdapter = ModuleAdapter(mutableListOf())  // Initialize with empty list
            modulesRecyclerView.layoutManager = LinearLayoutManager(itemView.context)

            modulesRecyclerView.adapter = moduleAdapter
            modulesRecyclerView.visibility = View.GONE


            // Handle semester selection
            semesterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    if(pos==0){
                        modulesRecyclerView.visibility = View.GONE
                        moduleAdapter.updatemodules(emptyList())

                    }else{
                        val semesterIndex = pos - 1
                        val selectedSemester = semesterNumbers[semesterIndex]
                        val modules = branch.semesters[selectedSemester] ?: emptyList()

                        // Update the module adapter
                        moduleAdapter.updatemodules(modules)

                        // Show RecyclerView only if there are modules for the selected semester
                        modulesRecyclerView.visibility = if (modules.isEmpty()) View.GONE else View.VISIBLE
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Hide RecyclerView if nothing is selected
                    modulesRecyclerView.visibility = View.GONE
                    moduleAdapter.updatemodules(emptyList()) // Clear adapter
                }
            }
            semesterSpinner.setSelection(0, false) // Set initial selection without triggering listener immediately if possible
        }
    }
}

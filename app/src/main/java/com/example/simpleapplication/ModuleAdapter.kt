package com.example.simpleapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ModuleAdapter(private var modules: MutableList<Modules>) :
    RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {

    // Update the modules list and notify the adapter of the change
    fun updatemodules(newModules: List<Modules>) {
        modules.clear()
        modules.addAll(newModules)
        notifyDataSetChanged()
    }

    inner class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.moduleView)
        private val credits: TextView = itemView.findViewById(R.id.CreditPart)

        // Bind the module data to the view
        fun bind(module: Modules) {
            name.text = module.name
            credits.text = "${module.credits} credits"
        }
    }

    // Create a new view holder and bind the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.modules, parent, false)
        return ModuleViewHolder(view)
    }

    // Bind data to the view holder at the given position
    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(modules[position])
    }

    // Return the number of modules in the list
    override fun getItemCount(): Int = modules.size
}

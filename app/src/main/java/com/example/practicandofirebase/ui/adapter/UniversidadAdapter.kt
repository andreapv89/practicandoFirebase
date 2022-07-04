package com.example.practicandofirebase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicandofirebase.R
import com.example.practicandofirebase.ui.model.UniversidadModel

class UniversidadAdapter (private var lstCourse:List<UniversidadModel>)
    : RecyclerView.Adapter<UniversidadAdapter.ViewHolder>()

{
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tv_item_universidad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_universidad,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstCourse[position]
        holder.tvNombre.text = item.nombre
    }

    override fun getItemCount(): Int {
        return lstCourse.size
    }
}
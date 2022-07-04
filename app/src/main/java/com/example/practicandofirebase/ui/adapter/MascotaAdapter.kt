package com.example.practicandofirebase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicandofirebase.R
import com.example.practicandofirebase.ui.model.MascotaModel

class MascotaAdapter(private var lstMascotas: List<MascotaModel>):
    RecyclerView.Adapter<MascotaAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivMascota: ImageView = itemView.findViewById(R.id.ivMascota)
        val tvMascota: TextView = itemView.findViewById(R.id.tvMascota)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFechaPerdida)
        val tvLugar: TextView = itemView.findViewById(R.id.tvLugar)
        val tvContacto: TextView = itemView.findViewById(R.id.tvContacto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_mascota,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstMascotas[position]
        holder.tvMascota.text = item.nommascota
        holder.tvFecha.text = item.fechaperdida
        holder.tvContacto.text = item.contacto
        holder.tvLugar.text = item.lugar
        Glide.with(holder.itemView.context)
            .load(item.urlimagen)
            .into(holder.ivMascota)

    }

    override fun getItemCount(): Int {
        return lstMascotas.size
    }

}
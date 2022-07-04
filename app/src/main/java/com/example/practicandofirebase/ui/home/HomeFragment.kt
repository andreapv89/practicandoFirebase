package com.example.practicandofirebase.ui.home

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.practicandofirebase.databinding.FragmentHomeBinding
import com.example.practicandofirebase.ui.model.UniversidadModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //codigo aqui
        var db =Firebase.firestore

        binding.btHomeRegistrar.setOnClickListener{
            val nombreUniversidad: String = binding.etHomeUniversidad.text.toString()
            val newUniversidad = UniversidadModel(nombreUniversidad)

            db.collection("universidades")
                .add(newUniversidad)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(requireContext(),"Universidad registrada : $nombreUniversidad", Toast.LENGTH_LONG).show()
                    Log.d(ContentValues.TAG, "Universidad agregada con ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(),"Error al registrar universidad", Toast.LENGTH_LONG).show()
                    Log.w(ContentValues.TAG, "Error agregando universidad", e)
                }
        }



        return root
    }
}
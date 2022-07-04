package com.example.practicandofirebase.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicandofirebase.R
import com.example.practicandofirebase.databinding.FragmentGalleryBinding
import com.example.practicandofirebase.ui.adapter.UniversidadAdapter
import com.example.practicandofirebase.ui.model.UniversidadModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Coding
        val view: View = inflater.inflate(R.layout.fragment_gallery, container, false)
        val db = Firebase.firestore
        val lstCourses: ArrayList<UniversidadModel> = ArrayList()
        val rvCourse: RecyclerView = binding.rvGalleryUniversidades

        db.collection("universidades")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
                    Log.e("Firebase Error","Ocurrió un error al cargar las universidades")
                    return@addSnapshotListener
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED ->{
                            lstCourses.add(
                                UniversidadModel(dc.document.data["nombre"].toString())
                            )
                        }
                    }
                }
                rvCourse.adapter = UniversidadAdapter(lstCourses)
                rvCourse.layoutManager = LinearLayoutManager(requireContext())

            }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
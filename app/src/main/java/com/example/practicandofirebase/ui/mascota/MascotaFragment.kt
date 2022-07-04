package com.example.practicandofirebase.ui.mascota

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicandofirebase.R
import com.example.practicandofirebase.databinding.FragmentMascotaBinding
import com.example.practicandofirebase.ui.adapter.MascotaAdapter
import com.example.practicandofirebase.ui.client.MascotaClient
import com.example.practicandofirebase.ui.model.MascotaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaFragment : Fragment() {

    private var _binding: FragmentMascotaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMascotaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Coding
        val rvMascota: RecyclerView = binding.rvMascotaMascotas
        rvMascota.layoutManager = LinearLayoutManager(requireActivity())

        var request: Call<List<MascotaModel>>
                = MascotaClient.retrofitService.listarMascota()

        request.enqueue(object: Callback<List<MascotaModel>> {
            override fun onResponse(
                call: Call<List<MascotaModel>>,
                response: Response<List<MascotaModel>>
            ) {
                rvMascota.adapter = MascotaAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<MascotaModel>>, t: Throwable) {
                Toast.makeText(requireContext(),"Error al cargar las mascotas", Toast.LENGTH_LONG).show()
            }
        })


        return root
    }



}
package com.example.tp2.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.adapters.ImageDetailAdapter
import com.example.tp2.data.network.flights.FlightService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log


class DetailsFragment : Fragment() {

    private lateinit var adapter: ImageDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ImageDetailAdapter(mutableListOf())

        val detailsBoracay = FlightService()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    detailsBoracay.getDetails()
                }

                val detailsList = response.data

                if (detailsList.isNotEmpty()) {
                    val details = detailsList[0]
                    print(details)
                    view?.let {
                        it.findViewById<TextView>(R.id.destination).text = details.destination
                        it.findViewById<TextView>(R.id.textView2).text = details.duration
                        it.findViewById<TextView>(R.id.textView3).text = details.country
                        it.findViewById<TextView>(R.id.textView4).text = details.price
                        it.findViewById<TextView>(R.id.textView10).text = details.description
                    }
                    // Extraer las imágenes de los detalles y actualizar el adaptador
                    val imageUrls = details.images
                    adapter.updateImages(imageUrls)
                } else {
                    run {
                        Log.e("DetailsFragment", "Error: Response is null")
                    }
                }
            } catch (e: Exception) {
                // Manejar cualquier excepción aquí
                Log.e("DetailsFragment", "Error: ${e.message}")
            }
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_details,container,false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.DetailsDestination)




        // Configurar el RecyclerView con el adaptador
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view

    }


}
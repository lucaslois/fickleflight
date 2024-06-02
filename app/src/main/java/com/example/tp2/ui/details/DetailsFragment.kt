package com.example.tp2.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2.adapters.ImageDetailAdapter
import com.example.tp2.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var adapter: ImageDetailAdapter
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getFlightDetails(args.destination)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeFlightDetails()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = ImageDetailAdapter(mutableListOf())
        binding.DetailsDestination.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.DetailsDestination.adapter = adapter
    }

    private fun observeFlightDetails() {
        viewModel.flightDetails.observe(viewLifecycleOwner) { details ->
            details?.let {
                with(binding) {
                    txtDetailCountry.text = details.country
                    txtDetailDestination.text = details.destination
                    txtDetailDuration.text = details.duration
                    txtDetailDescription.text = details.description
                    txtPrice.text = details.price
                }
                adapter.updateImages(details.images)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loader.root.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }


}
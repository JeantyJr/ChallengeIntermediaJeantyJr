package com.cursokotlinjun.challengemarvelappinter.ui.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.EventsComicsBinding
import com.cursokotlinjun.challengemarvelappinter.databinding.FragmentEventBinding
import com.cursokotlinjun.challengemarvelappinter.databinding.ViewEventsItemBinding
import com.cursokotlinjun.challengemarvelappinter.domain.model.EventDato
import com.cursokotlinjun.challengemarvelappinter.ui.character.informations.ComicsAdapter
import com.cursokotlinjun.challengemarvelappinter.util.extensions.comicsLinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.grpc.InternalChannelz.id

@AndroidEntryPoint
class EventFragment : Fragment(), EventAdapter.ComicInformations {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventViewModel by viewModels()
    private lateinit var eventAdapter : EventAdapter
    private val comicsAdapter = ComicsEventsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEventBinding.inflate(inflater, container, false)
       // _bindingTwo = EventsComicsBinding.inflate(inflater, container, false)

        eventAdapter = EventAdapter( this, viewModel::getComics)
        binding.eventsRecyclerView.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        setEventObservers()


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        eventAdapter.clear()
        viewModel.retry()
    }



    private fun setEventObservers(){
        viewModel.eventsStatus.observe(viewLifecycleOwner){
            with(binding){
                when(it) {
                    is EventViewModel.EventsStatus.Success -> {
                        eventAdapter.addAll(it.events)
                        eventsRecyclerView.isVisible = true
                    }
                    is EventViewModel.EventsStatus.Loading -> {
                        pbLoadingEvent.root.isVisible = it.isLoading
                    }
                    is EventViewModel.EventsStatus.Error -> {
                        eventsRecyclerView.isVisible = false
                    }
                    is EventViewModel.EventsStatus.ComicSuccess -> {
                        comicsAdapter.clear()
                        comicsAdapter.addAll(it.comics)
                    }
                }
            }
        }
    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
    override fun setComicsList(binding: ViewEventsItemBinding) {
        binding.recyclerComics.recyViewComics.apply {
            adapter = comicsAdapter
            layoutManager = comicsLinearLayoutManager(requireContext())
        }
    }

}



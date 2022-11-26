package com.cursokotlinjun.challengemarvelappinter.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.FragmentCharactersBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterViewModel by viewModels()
    private val adapter = CharacterAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        setObservers()
        setupCharactersList()
        setupPagination()


        return binding.root
    }

    private fun setupPagination(){
        binding.charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollVertically(1)){
                    viewModel.moreCharactersLoad()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
    private fun setupCharactersList(){
        binding.charactersRecyclerView.adapter = adapter
        adapter.onClickListener = {
            //  val action =  CharactersDirections.goToCharacterInformations(it)
            //     findNavController().navigate(action)
            CharactersFragmentDirections.goToCharacterInformations(it).apply {
                findNavController().navigate(this)
            }


        }
    }


    private fun setObservers(){
        viewModel.charactersStatus.observe(viewLifecycleOwner){
            when{
                it.isLoading -> {
                    loading()
                }
                it.isError ->{
                    retry()
                }
                it.isSucces.isNotEmpty() -> {
                    adapter.addAll(it.isSucces)
                    binding.progressBar.root.isVisible = false

                }
            }

        }
    }

    private fun loading(){
        binding.progressBar.root.isVisible = true
    }
    private fun retry(){
        val contextView = binding.charactersRecyclerView
        Snackbar.make(contextView, "No Internet! Retry", Snackbar.LENGTH_INDEFINITE).setAction("Retry"){
            setObservers()
        }
            .show()
    }



}

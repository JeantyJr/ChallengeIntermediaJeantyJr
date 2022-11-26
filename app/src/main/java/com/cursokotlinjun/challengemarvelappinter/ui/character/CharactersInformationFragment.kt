package com.cursokotlinjun.challengemarvelappinter.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlinjun.challengemarvelappinter.MainActivity
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.FragmentCharactersInformationBinding
import com.cursokotlinjun.challengemarvelappinter.ui.character.informations.CharacterInformationViewModel
import com.cursokotlinjun.challengemarvelappinter.ui.character.informations.ComicsAdapter
import com.cursokotlinjun.challengemarvelappinter.util.binding.setImage
import com.cursokotlinjun.challengemarvelappinter.util.extensions.comicsLinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersInformationFragment : Fragment(R.layout.fragment_characters_information) {
    private var _binding: FragmentCharactersInformationBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CharactersInformationFragmentArgs>()
    private val viewModel: CharacterInformationViewModel by viewModels()
    private val comicsAdapter = ComicsAdapter()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       _binding =  FragmentCharactersInformationBinding.inflate(inflater, container, false)
        setInformationsObservers()
        setUpView()

        return binding.root
    }

    private fun setInformationsObservers(){
        viewModel.comicStatus.observe(viewLifecycleOwner){
                when{
                    it.isSuccess.isNotEmpty() -> {
                        comicsAdapter.addAll(it.isSuccess)
                        hideLoadingAndShowView()
                    }
                    it.isLoading -> {
                        hideViewAndShowLoading()
                    }
                    it.isError -> {
                        retry()
                    }

                }

        }
    }

    private fun setUpView(){
        val mainActivity = activity as MainActivity
        val character = args.characterArgs
        with(binding){
            mainActivity.apply { mainActivity.setToolBarTitlle("Marvel")
                mainActivity.buttonNavigationHide()
            }
            setImage(imageViewCharaInfo, character.thumbnail)
            textViewDescrCharaInfo.text = character.description
                recyclerComics.recyViewComics.apply {
                layoutManager = comicsLinearLayoutManager(requireContext())
               // comicsLinearLayoutManager(requireContext())
                //comicsAdapter
                adapter = comicsAdapter
            }
            viewModel.getComics(character.id)
            }

    }



    private fun retry() {
        val contextView = binding.recyclerComics.recyViewComics
        Snackbar.make(contextView, "Wait a fwe second and try again", Snackbar.LENGTH_LONG)
            .setAction("RETRY") {
                // Responds to click on the action
                setInformationsObservers()

            }
            .show()
    }


    private fun hideViewAndShowLoading() {
        binding.progressBar.root.isVisible = true
        binding.layoutInformationsConst.isVisible = false
    }
    private fun hideLoadingAndShowView() {
        binding.progressBar.root.isVisible = false
        binding.layoutInformationsConst.isVisible = true
    }

    override fun onDestroyView(){
         val mainActivity = activity as MainActivity
         super.onDestroyView()
         mainActivity.setToolBarTitlle()
         mainActivity.buttonNavigationVisible()
         _binding = null
     }

}

package com.psp.collegeforum.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentMainBinding
import com.psp.collegeforum.ui.adapters.QuestionsAdapter
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.view.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: QuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation and buttons to other fragments
        // Add question btn
        view.fabAddQuestion.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addQuestion)
        }
        // Search btn
        view.imgBtnSearchQuestion.setOnClickListener {
          Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_searchFragment)
        }
         //Profile btn
        view.imgBtnProfile.setOnClickListener {
         Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_userProfileFragment)
        }


        binding.tv.text = "Pranit"
        viewmodel.getQuestions()

        adapter = QuestionsAdapter()

        viewmodel.question.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        val recyclerView = binding.rvInMainFrag
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

    }
}
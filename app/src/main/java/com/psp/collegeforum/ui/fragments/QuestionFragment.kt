package com.psp.collegeforum.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentMainBinding
import com.psp.collegeforum.databinding.FragmentQuestionBinding
import com.psp.collegeforum.ui.adapters.AnswerAdapter
import com.psp.collegeforum.ui.adapters.QuestionsAdapter
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.view.*

@AndroidEntryPoint
class QuestionFragment : Fragment(R.layout.fragment_question) {

    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var adapter: AnswerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.getAnswers()
        adapter = AnswerAdapter()

        viewmodel.answer.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        val recyclerView = binding.rvInQuestionFrag
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }


}
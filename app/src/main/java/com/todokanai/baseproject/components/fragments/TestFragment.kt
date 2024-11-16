package com.todokanai.baseproject.components.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.todokanai.baseproject.adapters.TestRecyclerAdapter
import com.todokanai.baseproject.databinding.FragmentTestBinding
import com.todokanai.baseproject.viewmodel.TestFragViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment : Fragment() {

    private val viewModel:TestFragViewModel by viewModels()
    private val binding by lazy{FragmentTestBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initVariables()

        // val context = requireContext()
        // val activity = requireActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val testAdapter = TestRecyclerAdapter(
            {viewModel.onItemClick(it)},
            {viewModel.onItemLongClick(it)}
        )
        binding.run{
            testRecyclerView.run {
                adapter = testAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            swipe.setOnRefreshListener {
                // refresh item list here
                swipe.isRefreshing = false
            }
        }

        return binding.root
    }

}
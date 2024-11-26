package com.todokanai.baseproject.components.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.todokanai.baseproject.adapters.MultiSelectTestAdapter
import com.todokanai.baseproject.adapters.TestRecyclerAdapter
import com.todokanai.baseproject.databinding.FragmentTestBinding
import com.todokanai.baseproject.viewmodel.TestFragViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment : Fragment() {

    private val viewModel:TestFragViewModel by viewModels()
    private val binding by lazy{FragmentTestBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val testAdapter = TestRecyclerAdapter(
            viewModel.itemFlow,
            {viewModel.onItemClick(it)},
            {viewModel.onItemLongClick(it)}
        )

        val multiTestAdapter = MultiSelectTestAdapter(
            viewModel.itemFlow
        ).apply {
            setHasStableIds(true)
        }
        binding.run{
            testRecyclerView.run {
                //adapter = testAdapter
                adapter = multiTestAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            swipe.run{
                setOnRefreshListener {
                    // refresh item list here
                    swipe.isRefreshing = false
                }
                viewTreeObserver.addOnScrollChangedListener() {
                    if(true) {
                        isEnabled = false
                    }else{
                        isEnabled = true
                    }
                }
            }
        }
        return binding.root
    }
}
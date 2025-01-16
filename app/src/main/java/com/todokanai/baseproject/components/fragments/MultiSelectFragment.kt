package com.todokanai.baseproject.components.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.todokanai.baseproject.adapters.MultiSelectTestAdapter
import com.todokanai.baseproject.adapters.TestRecyclerAdapter
import com.todokanai.baseproject.databinding.FragmentMultiSelectBinding
import com.todokanai.baseproject.viewmodel.MultiSelectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MultiSelectFragment : Fragment() {

    private val viewModel:MultiSelectViewModel by viewModels()
    private val binding by lazy{FragmentMultiSelectBinding.inflate(layoutInflater)}
    private val isSwipeEnabled = MutableStateFlow<Boolean>(true)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val testAdapter = TestRecyclerAdapter(
            viewModel.itemFlow,
            {viewModel.onItemClick(it)},
            {viewModel.onItemLongClick(it)},
        )

        val multiTestAdapter = MultiSelectTestAdapter(
            viewModel.itemFlow
        ).apply {
            setHasStableIds(true)
        }
        binding.run{
            testRecyclerView.run {
               // adapter = testAdapter
                adapter = multiTestAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            swipe.run{
                setOnRefreshListener {
                    // refresh item list here
                    swipe.isRefreshing = false
                }
                viewTreeObserver.addOnScrollChangedListener() {
                    isSwipeEnabled.asLiveData().observe(viewLifecycleOwner){
                        isEnabled = it      // enable/disable swipe
                    }
                }
            }
        }
        return binding.root
    }
}
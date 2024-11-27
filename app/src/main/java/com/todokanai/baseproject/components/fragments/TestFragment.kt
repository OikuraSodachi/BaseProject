package com.todokanai.baseproject.components.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.todokanai.baseproject.abstracts.AreaSelectAddon
import com.todokanai.baseproject.adapters.MultiSelectTestAdapter
import com.todokanai.baseproject.adapters.TestRecyclerAdapter
import com.todokanai.baseproject.databinding.FragmentTestBinding
import com.todokanai.baseproject.viewmodel.TestFragViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment : Fragment() {

    private val viewModel:TestFragViewModel by viewModels()
    private val binding by lazy{FragmentTestBinding.inflate(layoutInflater)}

    /** drag 활성화 여부 **/
    var enabled:Boolean = false
    val isTemporaryTest = true

    var isStartReady = false
    var startX : Float = 0f
    var startY : Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val testAddon = AreaSelectAddon(binding.testRecyclerView)
        val testAdapter = TestRecyclerAdapter(
            viewModel.itemFlow,
            {viewModel.onItemClick(it)},
          //  {viewModel.onItemLongClick(it)},
            //longClickListener
            testAddon.longClickListener
        )

        val multiTestAdapter = MultiSelectTestAdapter(
            viewModel.itemFlow
        ).apply {
            setHasStableIds(true)
        }
        binding.run{
            testRecyclerView.run {
                adapter = testAdapter
               // adapter = multiTestAdapter
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
        if(isTemporaryTest){
           // binding.testRecyclerView.visibility = GONE
            //onTest(binding)
            testAddon.add()
        }
        return binding.root
    }


    val longClickListener = View.OnLongClickListener {
        isStartReady = false
        it.setOnTouchListener { view, motionEvent ->
            if(isStartReady == false) {
                startX = motionEvent.x
                startY = motionEvent.y
                println("tag longClick x:${motionEvent.x}, y:${motionEvent.y}")
                isStartReady = true
            }
            false
        }

        enabled = true
        false
    }

    fun onTest(binding: FragmentTestBinding){
        binding.testRecyclerView.run{

            setOnTouchListener { view, motionEvent ->
                if( enabled ) {
                    println("x:${motionEvent.x}, y:${motionEvent.y}, action: ${motionEvent.action}")
                  //  onMovement(motionEvent.x,motionEvent.y)

                }
                if(enabled == true && motionEvent.action == 1){
                  //  wManager.removeView(area)

                    println("tag startX: $startX, startY: $startY")
                    println("tag endX: ${motionEvent.x}, endY: ${motionEvent.y}")

                    enabled = false
                }
                false
            }
        }
    }
}
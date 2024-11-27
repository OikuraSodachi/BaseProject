package com.todokanai.baseproject.components.fragments

import android.content.Context.WINDOW_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
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

    private val wManager by lazy{requireActivity().getSystemService(WINDOW_SERVICE) as WindowManager}

    /** drag 활성화 여부 **/
    var enabled:Boolean = false
    val isTemporaryTest = true
    var startX : Float = 0f
    var startY : Float = 0f

    val area by lazy {
        ConstraintLayout(requireActivity()).apply {
            alpha = 0f
            //setBackgroundColor(Color.RED)
            isFocusable = false
            isClickable = false
            left = startX.toInt()
            top = startY.toInt()
        }
    }

    val lp = WindowManager.LayoutParams().apply{
        this.flags =
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        //alpha = 0.3f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val testAdapter = TestRecyclerAdapter(
            viewModel.itemFlow,
            {viewModel.onItemClick(it)},
            //{viewModel.onItemLongClick(it)},
            longClickListener
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
            onTest(binding)
        }
        return binding.root
    }


    val longClickListener = View.OnLongClickListener {
        it.setOnTouchListener { view, motionEvent ->
            startX = motionEvent.x
            startY = motionEvent.y
            println("tag longClick x:${motionEvent.x}, y:${motionEvent.y}")

            false
        }
        println("longClick")
     //   wManager.addView(area,lp)



        enabled = true
        false
    }

    fun onTest(binding: FragmentTestBinding){
        binding.testRecyclerView.run{
          //  setOnLongClickListener()

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


    /** 끝 지점 **/
    fun onMovement(x:Float,y:Float){
     //   area.
        area.left = startX.toInt()
        area.top = startY.toInt()
        area.right = x.toInt()
        area.bottom = y.toInt()
        wManager.updateViewLayout(area,lp)
    }
}
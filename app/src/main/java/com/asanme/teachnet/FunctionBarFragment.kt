package com.asanme.teachnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FunctionBarFragment : Fragment() {
    companion object {

        fun newInstance(): FunctionBarFragment {
            return FunctionBarFragment()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.function_bar, container, false)
    }
}
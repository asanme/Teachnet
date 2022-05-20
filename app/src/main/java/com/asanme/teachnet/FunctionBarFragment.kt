package com.asanme.teachnet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asanme.teachnet.databinding.FunctionBarBinding

class FunctionBarFragment : Fragment() {
    lateinit var mView: View
    lateinit var binding : FunctionBarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FunctionBarBinding.inflate(layoutInflater)

        binding.homeBtn.setOnClickListener {
            startActivity(Intent(this@FunctionBarFragment.requireContext(), HomeActivity::class.java))
        }

        binding.searchBtn.setOnClickListener {
            startActivity(Intent(this@FunctionBarFragment.requireContext(), SearchActivity::class.java))
        }

        binding.timetableBtn.setOnClickListener {
            startActivity(Intent(this@FunctionBarFragment.requireContext(), TimetableActivity::class.java))
        }

        binding.notificationsBtn.setOnClickListener {
            startActivity(Intent(this@FunctionBarFragment.requireContext(), NotificationsActivity::class.java))
        }

        binding.profileBtn.setOnClickListener {
            startActivity(Intent(this@FunctionBarFragment.requireContext(), ProfileActivity::class.java))
        }

        return binding.root
    }
}
package com.asanme.teachnet.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asanme.teachnet.home.HomeActivity
import com.asanme.teachnet.timetable.TimetableActivity
import com.asanme.teachnet.databinding.FunctionBarBinding
import com.asanme.teachnet.notifications.NotificationsActivity
import com.asanme.teachnet.profile.ProfileActivity
import com.asanme.teachnet.search.SearchActivity

/**
 * This class represents a fragment for the bottom function bar present in almost all views.
 * We set onClickListeners in every button, those buttons will take the user to the desired view
 */
class FunctionBarFragment : Fragment() {
    lateinit var binding : FunctionBarBinding
    //TODO Add logic so that the function bar changes the button color depending on the screen
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
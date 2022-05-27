package com.asanme.teachnet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asanme.teachnet.R

/**
 * This fragment represents the search bar present in the home screen
 * Not functional yet.
 */
class SeachBarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_bar, container, false)
    }
}
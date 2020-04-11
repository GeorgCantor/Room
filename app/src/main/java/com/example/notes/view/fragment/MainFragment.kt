package com.example.notes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val fragment = BottomSheetFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            fragment.show(childFragmentManager, fragment.tag)
        }

        bottom_app_bar.setNavigationOnClickListener {
            fragment.show(childFragmentManager, fragment.tag)
        }

        bottom_app_bar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.app_bar_settings -> fragment.show(childFragmentManager, fragment.tag)
            }
            true
        }
    }
}
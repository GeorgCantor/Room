package com.example.notes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.util.observe
import com.example.notes.util.shortToast
import com.example.notes.view.fragment.bottom_sheet.ListBottomFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val fragment = ListBottomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            error.observe(viewLifecycleOwner) {
                context?.shortToast(it)
            }

            taskList.observe(viewLifecycleOwner) {
            }
        }

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
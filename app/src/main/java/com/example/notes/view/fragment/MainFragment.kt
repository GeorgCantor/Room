package com.example.notes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.util.Constants.ARG_SELECTED
import com.example.notes.util.PreferenceManager
import com.example.notes.util.observe
import com.example.notes.util.shortToast
import com.example.notes.view.fragment.bottom_sheet.list.ListBottomFragment
import com.example.notes.view.fragment.bottom_sheet.task.TaskBottomFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

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
        val prefManager = PreferenceManager(requireContext())
        val savedId = prefManager.getInt(ARG_SELECTED) ?: 0

        val selectedListId: Int = arguments?.getInt(ARG_SELECTED) ?: savedId
        prefManager.saveInt(ARG_SELECTED, selectedListId)

        viewModel.getTaskListById(selectedListId)

        with(viewModel) {
            error.observe(viewLifecycleOwner) {
                context?.shortToast(it)
            }

            taskList.observe(viewLifecycleOwner) {
                task_list_name.text = it.listName
            }
        }

        val listBottomFragment = ListBottomFragment()
        val taskBottomFragment = TaskBottomFragment()

        fab.setOnClickListener {
            taskBottomFragment.show(childFragmentManager, listBottomFragment.tag)
        }

        bottom_app_bar.setNavigationOnClickListener {
            listBottomFragment.show(childFragmentManager, listBottomFragment.tag)
        }

        bottom_app_bar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.app_bar_settings -> listBottomFragment.show(childFragmentManager, listBottomFragment.tag)
            }
            true
        }
    }
}
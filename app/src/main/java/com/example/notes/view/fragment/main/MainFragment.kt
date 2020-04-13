package com.example.notes.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.notes.R
import com.example.notes.model.db.entity.Task
import com.example.notes.util.Constants.ARG_SELECTED
import com.example.notes.util.Constants.ARG_TASK
import com.example.notes.util.getSelectedListId
import com.example.notes.util.observe
import com.example.notes.util.saveSelectedListId
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

        val selectedListId: Int = arguments?.getInt(ARG_SELECTED) ?: context?.getSelectedListId() ?: 0
        context?.saveSelectedListId(selectedListId)

        with(viewModel) {
            getTaskListById(selectedListId)
            getCommonTasksById(selectedListId)

            error.observe(viewLifecycleOwner) {
                context?.shortToast(it)
            }

            taskList.observe(viewLifecycleOwner) {
                task_list_name.text = it.listName
            }

            tasks.observe(viewLifecycleOwner) {
                tasks_recycler.adapter = TasksAdapter(it) {
                    findNavController(this@MainFragment).navigate(
                        R.id.action_mainFragment_to_taskFragment,
                        Bundle().apply {
                            putParcelable(
                                ARG_TASK, Task(
                                    id = it.id,
                                    taskListId = it.taskListId,
                                    taskName = it.taskName,
                                    taskDetails = it.taskDetails,
                                    taskDate = it.taskDate,
                                    taskCompleted = it.taskCompleted
                                )
                            )
                        }
                    )
                }
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
                R.id.app_bar_settings -> listBottomFragment.show(
                    childFragmentManager,
                    listBottomFragment.tag
                )
            }
            true
        }
    }
}
package com.example.notes.view.fragment.bottom_sheet.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.model.db.entity.Task
import com.example.notes.util.*
import com.example.notes.view.fragment.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_task_bottom.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class TaskBottomFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_task_bottom, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task_edit_text.requestFocus()

        requireActivity().showKeyBoard()

        with(viewModel) {
            error.observe(viewLifecycleOwner) {
                context?.shortToast(it)
            }

            save_text.setOnClickListener {
                val task = task_edit_text.text
                if (task.isNotBlank()) insertTask(
                    Task(
                        id = getRandomId(),
                        taskListId = context?.getSelectedListId() ?: 0,
                        taskName = task.toString(),
                        taskDetails = task.toString(),
                        taskDate = Date(),
                        taskCompleted = false
                    )
                )
                this@TaskBottomFragment.dismiss()
            }
        }
    }
}
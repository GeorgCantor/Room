package com.example.notes.view.fragment.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.model.db.entity.Subtask
import com.example.notes.model.db.entity.Task
import com.example.notes.util.Constants.ARG_TASK
import com.example.notes.util.getRandomId
import com.example.notes.view.fragment.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_task.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TaskFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_task, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val task: Task? = arguments?.getParcelable(ARG_TASK)

        val subtask = name_edit_text.text

        title.text = task?.taskName

        back_arrow.setOnClickListener { activity?.onBackPressed() }

        save_subtask.setOnClickListener {
            if (subtask.isNotBlank()) viewModel.insertSubtask(
                Subtask(
                    id = getRandomId(),
                    taskId = task?.id ?: 0,
                    subtaskName = subtask.toString(),
                    taskCompleted = task?.taskCompleted ?: false
                )
            )
        }
    }
}
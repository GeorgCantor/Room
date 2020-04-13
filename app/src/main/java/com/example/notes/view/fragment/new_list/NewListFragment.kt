package com.example.notes.view.fragment.new_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.model.db.entity.TaskList
import com.example.notes.view.fragment.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_new_list.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class NewListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel_image.setOnClickListener { activity?.onBackPressed() }
        done_text.setOnClickListener {
            val name = list_name_edit_text.text.toString()
            if (name.isNotBlank()) viewModel.insertTaskList(TaskList((0..999999).random(), name))
            activity?.onBackPressed()
        }
    }
}
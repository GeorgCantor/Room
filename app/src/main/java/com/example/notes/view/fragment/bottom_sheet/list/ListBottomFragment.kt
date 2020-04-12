package com.example.notes.view.fragment.bottom_sheet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.notes.R
import com.example.notes.util.Constants.ARG_SELECTED
import com.example.notes.util.observe
import com.example.notes.util.shortToast
import com.example.notes.view.fragment.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_list_bottom.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class ListBottomFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_bottom, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            error.observe(viewLifecycleOwner) {
                context?.shortToast(it)
            }

            taskLists.observe(viewLifecycleOwner) {
                task_list_recycler.adapter = ListBottomAdapter(it) { list ->
                    findNavController(this@ListBottomFragment).navigate(
                        R.id.action_mainFragment_self,
                        Bundle().apply { putInt(ARG_SELECTED, list.id) }
                    )
                }
            }
        }

        add_list_text.setOnClickListener {
            findNavController(this).navigate(R.id.action_mainFragment_to_newListFragment)
        }
    }
}
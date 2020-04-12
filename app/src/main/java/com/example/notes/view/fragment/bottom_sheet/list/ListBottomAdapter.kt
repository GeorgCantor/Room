package com.example.notes.view.fragment.bottom_sheet.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.db.entity.TaskList
import kotlinx.android.synthetic.main.item_task_list.view.*

class ListBottomAdapter(
    taskLists: MutableList<TaskList>,
    private val clickListener: (TaskList) -> Unit
) : RecyclerView.Adapter<ListBottomAdapter.ListBottomViewHolder>() {

    private val lists = mutableListOf<TaskList>()

    init {
        lists.addAll(taskLists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListBottomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_list, parent, false)
        )

    override fun getItemCount() = lists.size

    override fun onBindViewHolder(holder: ListBottomViewHolder, position: Int) {
        val taskList = lists[position]

        holder.name.text = taskList.listName
        holder.itemView.setOnClickListener { clickListener(taskList) }
    }

    class ListBottomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.list_name
    }
}
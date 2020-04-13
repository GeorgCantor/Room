package com.example.notes.view.fragment.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.CommonTask
import kotlinx.android.synthetic.main.item_subtask.view.*
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.item_task.view.line

class TasksAdapter(
    tasks: MutableList<CommonTask>,
    private val clickListener: (CommonTask) -> Unit,
    private val circleClickListener: (CommonTask) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_TASK = 0
        private const val TYPE_SUBTASK = 1
    }

    private val tasks = mutableListOf<CommonTask>()

    init {
        this.tasks.addAll(tasks)
        this.tasks.sortBy { it.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TASK -> {
                TaskViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
                )
            }
            TYPE_SUBTASK -> {
                SubTaskViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_subtask, parent, false)
                )
            }
            else -> TaskViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            )
        }
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]

        when (holder) {
            is TaskViewHolder -> {
                holder.name.text = task.taskName
                holder.itemView.setOnClickListener { clickListener(task) }
                holder.circle.setOnClickListener { circleClickListener(task) }
            }
            is SubTaskViewHolder -> {
                holder.name.text = task.taskName
                holder.itemView.setOnClickListener { clickListener(task) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (tasks[position].isSubtask) {
            true -> TYPE_SUBTASK
            false -> TYPE_TASK
        }
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.task_name
        val circle: ImageView = view.circle_image
        val bottomLine: View = view.line
    }

    class SubTaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.subtask_name
        val circle: ImageView = view.sub_circle_image
    }
}
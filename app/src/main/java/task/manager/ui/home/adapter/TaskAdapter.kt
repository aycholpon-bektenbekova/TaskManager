package task.manager.ui.home.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import task.manager.App
import task.manager.R
import task.manager.databinding.ItemTaskBinding
import task.manager.data.model.Task



class TaskAdapter(private val tasks: ArrayList<Task> = arrayListOf(),
                  val context: Context,
                  val activity: FragmentActivity?, private val onClick: (Task) -> Unit):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(R.color.black)
        } else (holder.itemView.setBackgroundColor(R.color.white))
    }

    fun addTask(task: Task) {
        tasks.add(0, task)
        notifyItemChanged(0)
    }

    fun addTasks(newtTasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(newtTasks)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {

            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.description

            itemView.setOnLongClickListener {
                deleteAlertDialog(task)
                return@setOnLongClickListener true
            }
        }
    }

     fun deleteAlertDialog(task: Task) {
         val alertDialogBuilder = AlertDialog.Builder(context)
         alertDialogBuilder.setTitle("Delete task")
         alertDialogBuilder.setMessage("Are you sure, you want to delete the task?")
         alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
             App.db.taskDao().delete(task)
             activity?.recreate()
         }
         alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
             dialog.dismiss()
         }
         alertDialogBuilder.show()
    }
}



package task.manager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import task.manager.App
import task.manager.R
import task.manager.databinding.FragmentTaskBinding
import task.manager.data.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                if (task !== null) {
                    updateTask()
                } else {
                    saveTusk()
                }
            }else {
                    binding.etTitle.error = "Заполните поле"
                }
            }
        }

    private fun updateTask() {
        task?.title = binding.etTitle.text.toString()
        task?.description = binding.etDesc.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    private fun saveTusk(){
        val data = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }
}




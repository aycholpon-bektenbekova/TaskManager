/*package task.manager.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import task.manager.App


/*class DeleteTaskDialog : DialogFragment() {

   /* override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

       /* return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do u want to delete this task?")
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, id ->
                        App.db.taskDao().delete(task)
                    })
                .setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}*/
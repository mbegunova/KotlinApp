package com.project.contactskotlin


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {
//, positive : String, negative: String
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выбор есть всегда")
                .setMessage("Выбери пищу")
                .setCancelable(true)
                .setPositiveButton("Вкусная пища") { dialog, id ->
                    Toast.makeText(activity, "Вы сделали правильный выбор",
                        Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Здоровая пища",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(activity, "Возможно вы правы",
                            Toast.LENGTH_LONG).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

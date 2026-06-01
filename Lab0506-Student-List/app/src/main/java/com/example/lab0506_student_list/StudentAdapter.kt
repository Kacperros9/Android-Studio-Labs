package com.example.lab0506_student_list

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class StudentAdapter(
    context: Context,
    resource: Int,
    objects: MutableList<Student>
) : ArrayAdapter<Student>(context, resource, objects) {

    private class ViewHolder(view: View) {
        val desc: TextView = view.findViewById(R.id.textView_name)
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#F5F5F5"))
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        val student = getItem(position)
        student?.let { currentStudent ->
            holder.desc.text = currentStudent.toString()
            holder.avatar.setImageResource(currentStudent.photo)

            holder.btnDelete.setOnClickListener {
                val dialog = android.app.AlertDialog.Builder(context)
                    .setTitle("Potwierdzenie")
                    .setMessage("Czy na pewno chcesz usunąć studenta ${currentStudent.firstName} ${currentStudent.lastName}?")
                    .setPositiveButton("Tak") { _, _ ->
                        Student.ALL_STUDENTS.remove(currentStudent)
                        this@StudentAdapter.remove(currentStudent)
                        notifyDataSetChanged()
                        android.widget.Toast.makeText(context, "Usunięto: ${currentStudent.firstName} ${currentStudent.lastName}", android.widget.Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Nie", null)
                    .create()

                dialog.show()

                dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(android.graphics.Color.parseColor("#333333"))
                dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(android.graphics.Color.parseColor("#333333"))
            }
        }

        return view
    }
}
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
import android.widget.Toast

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
                remove(currentStudent)
                notifyDataSetChanged()
                Toast.makeText(context, "Usunięto studenta!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
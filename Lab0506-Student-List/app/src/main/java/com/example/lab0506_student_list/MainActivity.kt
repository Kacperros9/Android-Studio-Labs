package com.example.lab0506_student_list

import android.os.Build
import android.os.Bundle
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.lab0506_student_list.databinding.ActivityMainBinding

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var currentPhotoResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Student.ALL_STUDENTS.isEmpty()) {
            val images = arrayOf("student_0", "student_1", "student_2", "student_3", "student_4")
            val ids = IntArray(images.size)
            for (i in images.indices) {
                ids[i] = resources.getIdentifier(images[i], "drawable", packageName)
            }
            Student.fillStudents(ids)
        }

        studentAdapter = StudentAdapter(this, R.layout.list_item, Student.ALL_STUDENTS)
        binding.listWithAllStudents.adapter = studentAdapter

        binding.listWithAllStudents.setOnItemClickListener { _, _, position, _ ->
            val selected = studentAdapter.getItem(position)
            selected?.let {
                val lastName = it.lastName
                val age = it.getAge().toString()
                binding.displayView.text = "$lastName: $age"

                currentPhotoResId = it.photo
                binding.currentImage.setImageResource(currentPhotoResId)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                studentAdapter.filter.filter(newText)
                return false
            }
        })

        binding.accordingToNames.setOnClickListener {
            studentAdapter.sort(Student.comparatorAccordingToNames)
        }

        binding.avgAsc.setOnClickListener {
            studentAdapter.sort(Student.comparatorAvgAsc)
        }

        binding.avgDesc.setOnClickListener {
            studentAdapter.sort(Student.comparatorAvgDesc)
        }

        if (savedInstanceState != null) {
            val savedText = savedInstanceState.getString("SAVED_TEXT")
            currentPhotoResId = savedInstanceState.getInt("SAVED_PHOTO")

            binding.displayView.text = savedText
            if (currentPhotoResId != 0) {
                binding.currentImage.setImageResource(currentPhotoResId)
            }
        } else {
            if (Student.ALL_STUDENTS.isNotEmpty()) {
                val firstStudent = Student.ALL_STUDENTS[0]
                currentPhotoResId = firstStudent.photo
                binding.currentImage.setImageResource(currentPhotoResId)
                binding.displayView.text = "${firstStudent.lastName}: ${firstStudent.getAge()}"
            }
        }

        studentAdapter.sort(Student.comparatorAccordingToNames)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SAVED_TEXT", binding.displayView.text.toString())
        outState.putInt("SAVED_PHOTO", currentPhotoResId)
    }
}
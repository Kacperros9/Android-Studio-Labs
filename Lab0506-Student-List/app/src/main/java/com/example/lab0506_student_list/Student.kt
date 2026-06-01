package com.example.lab0506_student_list

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.Collator
import java.time.LocalDate
import java.time.Period
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class Student(
    var firstName: String = "Jan",
    var lastName: String = "Kowalski",
    var field: String = "Informatyka",
    var dataOfBirth: String = "1999-12-12",
    var avg: Double = 4.5,
    var photo: Int = 0
) : Comparable<Student> {

    init {
        ALL_STUDENTS.add(this)
    }

    override fun toString(): String {
        return "Imię: $firstName\nNazwisko: $lastName\nKierunek: $field\nŚrednia: $avg"
    }

    override fun compareTo(other: Student): Int {
        val collator = Collator.getInstance(Locale("pl", "PL"))
        val compareLastNames = collator.compare(lastName, other.lastName)
        val compareFirstNames = collator.compare(firstName, other.firstName)
        return if (compareLastNames == 0) compareFirstNames else compareLastNames
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge(): Double {
        val year = dataOfBirth.substring(0, 4).toInt()
        val month = dataOfBirth.substring(5, 7).toInt()
        val day = dataOfBirth.substring(8, 10).toInt()

        val date = LocalDate.of(year, month, day)
        val per = Period.between(date, TODAY)

        val res = per.years + per.months / 12.0 + per.days / 365.0
        return String.format(Locale.US, "%.2f", res).toDouble()
    }

    companion object {
        val ALL_STUDENTS = ArrayList<Student>()
        val TODAY: LocalDate = LocalDate.now()

        val comparatorAvgAsc = Comparator<Student> { s1, s2 ->
            s1.avg.compareTo(s2.avg)
        }

        val comparatorAvgDesc = Comparator<Student> { s1, s2 ->
            s2.avg.compareTo(s1.avg)
        }

        val comparatorAccordingToNames = Comparator<Student> { s1, s2 ->
            s1.compareTo(s2)
        }

        fun fillStudents(idsOfPhotos: IntArray) {
            val firstNames = arrayOf("Jacek", "Jan", "Marek", "Sylwester", "Jerzy")
            val lastNames = arrayOf("Kowalski", "Nowak", "Zieliński", "Wójcik", "Dąbrowski")
            val fields = arrayOf("Informatyka", "Automatyka", "Zarządzanie", "Ekonomia", "Prawo")
            val dates = arrayOf("1998-01-11", "1995-05-08", "2000-11-11", "1995-03-18", "2005-05-05")
            val averages = doubleArrayOf(4.5, 3.5, 4.8, 5.0, 4.3)

            for (i in dates.indices) {
                Student(firstNames[i], lastNames[i], fields[i], dates[i], averages[i], idsOfPhotos[i])
            }
        }
    }
}
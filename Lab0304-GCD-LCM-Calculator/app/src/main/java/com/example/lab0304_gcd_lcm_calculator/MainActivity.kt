package com.example.lab0304_gcd_lcm_calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab0304_gcd_lcm_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalculateNWD.setOnClickListener {
            calculateAndDisplayResult(isNWD = true)
        }

        binding.btnCalculateNWW.setOnClickListener {
            calculateAndDisplayResult(isNWD = false)
        }
    }

    private fun calculateAndDisplayResult(isNWD: Boolean) {
        binding.tilFirstNumber.error = null
        binding.tilSecondNumber.error = null
        binding.tvResult.text = "Wynik pojawi się tutaj"

        val num1Str = binding.etFirstNumber.text.toString()
        val num2Str = binding.etSecondNumber.text.toString()

        var hasError = false

        if (num1Str.isEmpty()) {
            binding.tilFirstNumber.error = "To pole nie może być puste!"
            hasError = true
        } else if (num1Str.toLong() == 0L) {
            binding.tilFirstNumber.error = "Liczba musi być większa od 0!"
            hasError = true
        }

        if (num2Str.isEmpty()) {
            binding.tilSecondNumber.error = "To pole nie może być puste!"
            hasError = true
        } else if (num2Str.toLong() == 0L) {
            binding.tilSecondNumber.error = "Liczba musi być większa od 0!"
            hasError = true
        }

        if (hasError) return

        val a = num1Str.toLong()
        val b = num2Str.toLong()

        if (isNWD) {
            val nwd = calculateNWD(a, b)
            binding.tvResult.text = "NWD($a, $b) = $nwd"
        } else {
            val nww = calculateNWW(a, b)
            binding.tvResult.text = "NWW($a, $b) = $nww"
        }
    }

    private fun calculateNWD(a: Long, b: Long): Long {
        var num1 = a
        var num2 = b
        while (num2 != 0L) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    private fun calculateNWW(a: Long, b: Long): Long {
        return (a * b) / calculateNWD(a, b)
    }
}
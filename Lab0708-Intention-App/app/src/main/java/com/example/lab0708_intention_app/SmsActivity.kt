package com.example.lab0708_intention_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SmsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        val etRecipient = findViewById<TextInputEditText>(R.id.etRecipient)
        val etContent = findViewById<TextInputEditText>(R.id.etContent)
        val btnSend = findViewById<Button>(R.id.btnSendSms)
        val btnBack = findViewById<Button>(R.id.btnBackSms)

        btnSend.setOnClickListener {
            val recipient = etRecipient.text.toString()
            val message = etContent.text.toString()

            val uri = Uri.parse("smsto:$recipient")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", message)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
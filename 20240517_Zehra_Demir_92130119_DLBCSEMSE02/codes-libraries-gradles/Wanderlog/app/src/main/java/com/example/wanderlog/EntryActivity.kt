package com.example.wanderlog

import BaseActivity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class EntryActivity : BaseActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var dateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        titleEditText = findViewById(R.id.editTextTitle)
        contentEditText = findViewById(R.id.editTextContent)
        saveButton = findViewById(R.id.buttonSave)
        dateEditText = findViewById(R.id.editTextDate)

        dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()
            val date = dateEditText.text.toString().trim() // Get the selected date

            if (title.isNotEmpty() && content.isNotEmpty() && date.isNotEmpty()) {
                val entry = JournalEntry(title = title, content = content, date = date) // Pass the selected date
                val id = DatabaseHelper.getInstance(this).addEntry(entry)
                entry.id = id.toInt()
                finish()
            } else {
                // Show error message if title, content, or date is empty
                Toast.makeText(this, "Title, content, and date cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                dateEditText.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }
}


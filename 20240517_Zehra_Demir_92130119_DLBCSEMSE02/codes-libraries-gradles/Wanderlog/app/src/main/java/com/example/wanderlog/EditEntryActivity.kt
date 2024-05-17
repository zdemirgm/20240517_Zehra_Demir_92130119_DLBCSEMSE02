package com.example.wanderlog

import BaseActivity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class EditEntryActivity : BaseActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var selectDateButton: Button
    private var selectedDate: Date? = null
    private var entryId: Int = -1 // Add entryId field

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_entry)

        titleEditText = findViewById(R.id.editTextTitle)
        contentEditText = findViewById(R.id.editTextContent)
        saveButton = findViewById(R.id.buttonSave)
        selectDateButton = findViewById(R.id.buttonSelectDate)

        selectDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        // Retrieve entry ID from intent
        entryId = intent.getIntExtra("ENTRY_ID", -1)

        // Fetch entry details from database using the ID and populate the EditText fields
        val entry = DatabaseHelper.getInstance(this).getEntryById(entryId)
        titleEditText.setText(entry.title)
        contentEditText.setText(entry.content)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty() && selectedDate != null) {
                // Update the entry in the database
                val updatedEntry = JournalEntry(entryId, title, content, selectedDate!!.toString())
                val rowsAffected = DatabaseHelper.getInstance(this).updateEntry(updatedEntry)
                if (rowsAffected > 0) {
                    // Entry updated successfully
                    Toast.makeText(this, "Entry updated", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // Failed to update entry
                    Toast.makeText(this, "Failed to update entry", Toast.LENGTH_SHORT).show()
                }
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
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                selectedDate = calendar.time
                updateDateInView()
            }, year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun updateDateInView() {
        // Update the date display in the UI
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        selectDateButton.text = dateFormat.format(selectedDate)
    }
}





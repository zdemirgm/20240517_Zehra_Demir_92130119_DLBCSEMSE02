package com.example.wanderlog

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class EntryDetailActivity : BaseActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var editEntryButton: Button
    private lateinit var deleteEntryButton: Button
    private var entryId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_detail)

        titleTextView = findViewById(R.id.textViewTitle)
        contentTextView = findViewById(R.id.textViewContent)
        dateTextView = findViewById(R.id.textViewDate)

        // Retrieve entry ID from intent
        entryId = intent.getIntExtra("ENTRY_ID", -1)

        // Set click listener for edit entry button
        editEntryButton = findViewById(R.id.buttonEditEntry)
        editEntryButton.setOnClickListener {
            // Handle edit button click
            val intent = Intent(this, EditEntryActivity::class.java)
            intent.putExtra("ENTRY_ID", entryId)
            startActivity(intent)
        }

        // Set click listener for delete entry button
        deleteEntryButton = findViewById(R.id.buttonDeleteEntry)
        deleteEntryButton.setOnClickListener {
            // Handle delete button click
            deleteEntry()
        }

        // Load entry details
        loadEntryDetails()
    }

    private fun loadEntryDetails() {
        // Fetch entry details from database
        val entry = DatabaseHelper.getInstance(this).getEntryById(entryId)

        // Display entry details
        titleTextView.text = entry.title
        contentTextView.text = entry.content
        dateTextView.text = entry.date
    }

    private fun deleteEntry() {
        // Delete the entry from the database using its ID
        val rowsAffected = DatabaseHelper.getInstance(this).deleteEntryById(entryId)

        // Check if deletion was successful
        if (rowsAffected > 0) {
            // Entry deleted successfully
            // Optionally, you can navigate back to the previous activity
            finish()
        } else {
            // Failed to delete entry
            // You can display a message to the user indicating the failure
            // For example:
            Toast.makeText(this, "Failed to delete entry", Toast.LENGTH_SHORT).show()
        }
    }
}


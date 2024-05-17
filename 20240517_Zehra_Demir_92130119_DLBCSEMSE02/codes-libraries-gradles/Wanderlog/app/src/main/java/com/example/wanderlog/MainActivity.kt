package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var entries: ArrayList<JournalEntry>
    private lateinit var addEntryButton: Button
    private lateinit var pinsButton: Button
    private lateinit var pinsList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewEntries)
        entries = ArrayList()
        pinsList = ArrayList()

        // Load entries from the database
        entries.addAll(DatabaseHelper.getInstance(this).getAllEntries())

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, entries)
        listView.adapter = adapter

        // Handle item click to view entry details
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedEntry = entries[position]
            val intent = Intent(this, EntryDetailActivity::class.java)
            intent.putExtra("ENTRY_ID", selectedEntry.id)
            startActivity(intent)
        }

        // Initialize add entry button and set click listener
        addEntryButton = findViewById(R.id.buttonAddEntry)
        addEntryButton.setOnClickListener {
            val intent = Intent(this, EntryActivity::class.java)
            startActivity(intent)
        }

        // Initialize pins button and set click listener
        pinsButton = findViewById(R.id.buttonPins)
        pinsButton.setOnClickListener {
            val intent = Intent(this, PinsActivity::class.java)
            intent.putStringArrayListExtra("pinsList", pinsList)
            startActivity(intent)
        }

        // Check conditions to update pins list
        checkAndUpdatePinsList()
    }

    private fun checkAndUpdatePinsList() {
        // Check for conditions to earn pins and add them to the pins list
        if (entries.size >= 1 && !pinsList.contains("You wrote your first journal entry!")) {
            pinsList.add("You wrote your first journal entry!")
        }

        if (entries.size >= 5 && !pinsList.contains("You wrote 5 journal entries!")) {
            pinsList.add("You wrote 5 journal entries!")
        }

        // Add more conditions to earn other pins as needed

        // Ensure the pins list is up to date before starting PinsActivity
        pinsButton.setOnClickListener {
            val intent = Intent(this, PinsActivity::class.java)
            intent.putStringArrayListExtra("pinsList", pinsList)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh entry list when returning from com.example.wanderlog.EntryActivity
        entries.clear()
        entries.addAll(DatabaseHelper.getInstance(this).getAllEntries())
        (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()

        // Update pins list based on new entries
        checkAndUpdatePinsList()
    }
}




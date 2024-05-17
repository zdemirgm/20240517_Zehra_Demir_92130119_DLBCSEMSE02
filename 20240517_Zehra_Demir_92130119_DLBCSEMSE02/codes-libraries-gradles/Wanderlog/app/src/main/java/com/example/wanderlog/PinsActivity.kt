package com.example.wanderlog

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class PinsActivity : BaseActivity() {

    private lateinit var pinsDescription: ListView
    private lateinit var sharePinsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pins)

        // Initialize views
        pinsDescription = findViewById(R.id.listViewPins)
        sharePinsButton = findViewById(R.id.sharePinsButton)

        // Get the list of pins from intent
        val pinsList = intent.getStringArrayListExtra("pinsList")

        // Check if pins list is null or empty
        if (pinsList.isNullOrEmpty()) {
            // Display a message indicating no pins are available
            Toast.makeText(this, "No pins available", Toast.LENGTH_SHORT).show()
            // Finish activity since there are no pins to display
            finish()
            return
        }

        // Display pins list
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pinsList)
        pinsDescription.adapter = adapter

        // Share pins button click listener
        sharePinsButton.setOnClickListener {
            sharePins()
        }
    }

    private fun sharePins() {
        // Share pins on Instagram
        val instagramIntent = Intent(Intent.ACTION_SEND)
        instagramIntent.type = "text/plain"
        instagramIntent.putExtra(Intent.EXTRA_TEXT, "Check out my pins on wanderlog.com")
        instagramIntent.setPackage("com.instagram.android")

        // Share pins on Twitter
        val twitterIntent = Intent(Intent.ACTION_SEND)
        twitterIntent.type = "text/plain"
        twitterIntent.putExtra(Intent.EXTRA_TEXT, "Check out my pins on wanderlog.com")
        twitterIntent.setPackage("com.twitter.android")

        // Share pins on Facebook
        val facebookIntent = Intent(Intent.ACTION_SEND)
        facebookIntent.type = "text/plain"
        facebookIntent.putExtra(Intent.EXTRA_TEXT, "Check out my pins on wanderlog.com")
        facebookIntent.setPackage("com.facebook.katana")

        // Create a chooser dialog to allow the user to select the sharing app
        val chooserIntent = Intent.createChooser(Intent(), "Share via")

        // Add the sharing intents to the chooser dialog
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(instagramIntent, twitterIntent, facebookIntent))

        // Start the chooser dialog
        startActivity(chooserIntent)
    }


}



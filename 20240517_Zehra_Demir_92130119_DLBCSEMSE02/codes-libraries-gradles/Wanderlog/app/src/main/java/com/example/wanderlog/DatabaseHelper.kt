package com.example.wanderlog

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper public constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2 // Increment version to 2
        private const val DATABASE_NAME = "WanderlogDB"
        private const val TABLE_ENTRIES = "entries"

        // Column names including the date
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_CONTENT = "content"
        private const val KEY_DATE = "date" // New column for date

        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createEntriesTable = ("CREATE TABLE $TABLE_ENTRIES($KEY_ID INTEGER PRIMARY KEY, $KEY_TITLE TEXT, $KEY_CONTENT TEXT, $KEY_DATE TEXT)") // Add date column
        db.execSQL(createEntriesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ENTRIES")
        onCreate(db)
    }

    fun addEntry(entry: JournalEntry): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE, entry.title)
        values.put(KEY_CONTENT, entry.content)
        values.put(KEY_DATE, entry.date) // Insert date into database

        val id = db.insert(TABLE_ENTRIES, null, values)
        db.close()
        return id
    }

    fun getEntryById(id: Int): JournalEntry {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_ENTRIES, arrayOf(KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE), "$KEY_ID=?", arrayOf(id.toString()), null, null, null)
        cursor?.moveToFirst()
        val entry = JournalEntry(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)) // Retrieve date from cursor
        cursor.close()
        return entry
    }

    @SuppressLint("Range")
    fun getAllEntries(): List<JournalEntry> {
        val entryList = ArrayList<JournalEntry>()
        val selectQuery = "SELECT * FROM $TABLE_ENTRIES"

        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val entry = JournalEntry(date = "")
                    entry.id = it.getInt(it.getColumnIndex(KEY_ID))
                    entry.title = it.getString(it.getColumnIndex(KEY_TITLE))
                    entry.content = it.getString(it.getColumnIndex(KEY_CONTENT))
                    entry.date = it.getString(it.getColumnIndex(KEY_DATE)) // Retrieve date from cursor
                    entryList.add(entry)
                } while (it.moveToNext())
            }
        }

        db.close()
        return entryList
    }

    fun updateEntry(entry: JournalEntry): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE, entry.title)
        values.put(KEY_CONTENT, entry.content)
        values.put(KEY_DATE, entry.date) // Update date in database

        val rowsAffected = db.update(TABLE_ENTRIES, values, "$KEY_ID=?", arrayOf(entry.id.toString()))
        db.close()
        return rowsAffected
    }

    fun deleteEntryById(id: Int): Int {
        val db = this.writableDatabase
        val rowsAffected = db.delete(TABLE_ENTRIES, "$KEY_ID=?", arrayOf(id.toString()))
        db.close()
        return rowsAffected
    }

}


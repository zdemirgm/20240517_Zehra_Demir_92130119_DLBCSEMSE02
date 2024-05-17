package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AddEntryUnitTest {

    @Test
    public void testAddEntry() {
        // Simulate adding a new entry
        JournalEntry entry = new JournalEntry(1, "New Entry", "This is a new entry content", "2024-05-17");

        // Check if the entry is added successfully
        assertEquals(1, entry.getId());
        assertEquals("New Entry", entry.getTitle());
        assertEquals("This is a new entry content", entry.getContent());
        assertEquals("2024-05-17", entry.getDate());
    }
}






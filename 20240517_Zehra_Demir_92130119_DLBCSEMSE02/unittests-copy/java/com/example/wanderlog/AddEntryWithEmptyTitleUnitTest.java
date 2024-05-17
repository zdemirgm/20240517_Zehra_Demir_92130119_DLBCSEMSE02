package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AddEntryWithEmptyTitleUnitTest {

    @Test
    public void testAddEntryWithEmptyTitle() {
        // Simulate adding a new entry with an empty title
        JournalEntry entry = new JournalEntry(1, "", "This is a content", "2024-05-17");

        // Check if the entry's title is empty
        assertEquals("", entry.getTitle());
    }
}

package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AddEntryWithEmptyContentUnitTest {

    @Test
    public void testAddEntryWithEmptyContent() {
        // Simulate adding a new entry with empty content
        JournalEntry entry = new JournalEntry(1, "New Entry", "", "2024-05-17");

        // Check if the entry's content is empty
        assertEquals("", entry.getContent());
    }
}

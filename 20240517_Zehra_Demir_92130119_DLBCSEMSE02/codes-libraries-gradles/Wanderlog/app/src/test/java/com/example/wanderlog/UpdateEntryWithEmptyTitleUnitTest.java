package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UpdateEntryWithEmptyTitleUnitTest {

    @Test
    public void testUpdateEntryWithEmptyTitle() {
        // Simulate updating an entry with an empty title
        JournalEntry entry = new JournalEntry(1, "", "This is an updated entry content", "2024-05-18");

        // Check if the entry's title is empty
        assertEquals("", entry.getTitle());
    }
}

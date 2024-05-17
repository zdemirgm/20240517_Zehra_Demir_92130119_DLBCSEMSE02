package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UpdateEntryUnitTest {

    @Test
    public void testUpdateEntry() {
        // Simulate updating an existing entry
        JournalEntry entry = new JournalEntry(1, "Updated Entry", "This is an updated entry content", "2024-05-18");

        // Check if the entry is updated successfully
        assertEquals(1, entry.getId());
        assertEquals("Updated Entry", entry.getTitle());
        assertEquals("This is an updated entry content", entry.getContent());
        assertEquals("2024-05-18", entry.getDate());
    }
}

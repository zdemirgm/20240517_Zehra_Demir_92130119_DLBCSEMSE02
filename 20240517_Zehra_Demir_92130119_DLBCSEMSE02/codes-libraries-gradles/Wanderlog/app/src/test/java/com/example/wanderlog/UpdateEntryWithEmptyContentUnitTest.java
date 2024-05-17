package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UpdateEntryWithEmptyContentUnitTest {

    @Test
    public void testUpdateEntryWithEmptyContent() {
        // Simulate updating an entry with empty content
        JournalEntry entry = new JournalEntry(1, "Updated Entry", "", "2024-05-18");

        // Check if the entry's content is empty
        assertEquals("", entry.getContent());
    }
}

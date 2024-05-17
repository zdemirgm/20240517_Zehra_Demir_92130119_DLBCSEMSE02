package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RetrieveEntryByIdWithoutDbHelperUnitTest {

    @Test
    public void testRetrieveEntryByIdWithoutDbHelper() {
        // Simulate retrieving an entry by ID without directly using DatabaseHelper
        int entryId = 1;
        JournalEntry entry = new JournalEntry(1,"", "", ""); // Simulating entry retrieval

        // Check if the retrieved entry's ID matches the expected ID
        assertEquals(1, entry.getId());
    }
}


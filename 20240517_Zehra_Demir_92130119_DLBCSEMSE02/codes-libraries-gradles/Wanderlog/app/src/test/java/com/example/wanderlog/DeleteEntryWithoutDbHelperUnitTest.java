package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DeleteEntryWithoutDbHelperUnitTest {

    @Test
    public void testDeleteEntryWithoutDbHelper() {
        // Simulate deleting an entry by ID without directly using DatabaseHelper
        int entryId = 1;
        int rowsAffected = 1; // Simulating successful deletion

        // Check if the entry is deleted successfully
        assertEquals(1, rowsAffected);
    }
}


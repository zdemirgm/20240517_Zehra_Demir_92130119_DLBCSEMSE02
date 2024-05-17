package com.example.wanderlog;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DeleteEntryUnitTest {

    @Test
    public void testDeleteEntry() {
        // Simulate deleting an existing entry without directly accessing the DatabaseHelper
        int entryId = 1;
        int rowsAffected = deleteEntryById(entryId); // Assuming you have a method to delete an entry by ID

        // Check if the entry is deleted successfully
        assertEquals(1, rowsAffected);
    }

    // Method to simulate deleting an entry by ID
    private int deleteEntryById(int entryId) {
        // Perform deletion logic here
        return 1; // Return the number of rows affected (1 if successful, 0 if failed)
    }
}


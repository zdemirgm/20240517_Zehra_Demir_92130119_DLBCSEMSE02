package com.example.wanderlog

data class JournalEntry(
    var id: Int = -1,
    var title: String = "",
    var content: String = "",
    var date: String
) {
    override fun toString(): String {
        return title
    }
}



package se.gustavkarlsson.noted.entities

interface Note {
    val id: Long?
    val title: String
    val content: String
}

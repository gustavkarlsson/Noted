package se.gustavkarlsson.noted.entities

data class Note(val id: Long? = null, val title: String = "", val content: String = "") {
    constructor(title: String, content: String) : this(null, title, content)
}

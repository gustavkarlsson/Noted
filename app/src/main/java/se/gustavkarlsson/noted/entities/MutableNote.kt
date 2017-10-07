package se.gustavkarlsson.noted.entities

data class MutableNote(
    override val id: Long? = null,
    val mutableTitle: StringBuilder,
    val mutableContent: StringBuilder
) : Note {
    constructor(note: Note) : this(note.id, StringBuilder(note.title), StringBuilder(note.content))

    override val title: String
        get() = mutableTitle.toString()
    override val content: String
        get() = mutableContent.toString()
}

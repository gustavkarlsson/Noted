package se.gustavkarlsson.noted.entities

data class ImmutableNote(
    override val id: Long? = null,
    override val title: String = "",
    override val content: String = ""
) : Note {
    companion object {
        val EMPTY = ImmutableNote()
    }
}

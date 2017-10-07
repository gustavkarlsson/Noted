package se.gustavkarlsson.noted.actions

import se.gustavkarlsson.noted.services.database.entities.Note

interface EditNote : (Note) -> Unit

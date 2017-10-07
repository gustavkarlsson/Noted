package se.gustavkarlsson.noted.actions

import se.gustavkarlsson.noted.entities.Note

interface FindNoteById : (Long) -> Note?

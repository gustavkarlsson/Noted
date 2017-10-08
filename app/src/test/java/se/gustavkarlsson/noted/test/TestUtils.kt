package se.gustavkarlsson.noted.test

import se.gustavkarlsson.noted.services.database.DbNote

fun createSampleDbNote() = DbNote(1, "Feed the cat", "Feed it fish and chips")

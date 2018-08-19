package se.gustavkarlsson.noted.krate

import se.gustavkarlsson.krate.core.Store
import se.gustavkarlsson.krate.core.dsl.buildStore

typealias NoteStore = Store<State, Command, Result>
val store = buildStore<State, Command, Result> {

}

package se.gustavkarlsson.noted.gui.activities.editnote

import android.text.Editable
import android.text.TextWatcher

class SyncingTextWatcher(private val syncTarget: StringBuilder) : TextWatcher {

    override fun beforeTextChanged(oldText: CharSequence, start: Int, oldLength: Int, newLength: Int) = Unit

    override fun onTextChanged(newText: CharSequence, start: Int, oldLength: Int, newLength: Int) {
        val oldEnd = start + oldLength
        val newEnd = start + newLength
        val replacement = newText.subSequence(start, newEnd).toString()
        syncTarget.replace(start, oldEnd, replacement)
    }

    override fun afterTextChanged(newText: Editable) = Unit
}

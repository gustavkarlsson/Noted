package se.gustavkarlsson.noted.gui.activities.notes

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import se.gustavkarlsson.noted.entities.Note

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    private var actualData: List<Note> = emptyList()
    var data: List<Note>
        get() = actualData
        set(value) {
            actualData = value
            notifyDataSetChanged()
        }
    var onClick: ((Note) -> Unit)? = null
    var onSwipe: ((Note) -> Unit)? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        ItemTouchHelper(SwipeCallback()).attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        view.ellipsize = TextUtils.TruncateAt.END
        view.maxLines = 1
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note: Note, onClick: ((Note) -> Unit)?) {
            val textView = itemView as TextView
            textView.text = note.presentationText
            if (onClick != null) {
                itemView.setOnClickListener { onClick(note) }
            }
        }
    }

    inner class SwipeCallback : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) =
            makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (onSwipe != null) {
                val position = viewHolder.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val note = data[position]
                    onSwipe?.invoke(note)
                }
            }
        }

        override fun isLongPressDragEnabled() = false
    }

    private val Note.presentationText: String
        get() {
            if (title.isNotBlank()) {
                return title
            }
            return content
        }
}

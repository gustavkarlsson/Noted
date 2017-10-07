package se.gustavkarlsson.noted.activities

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import se.gustavkarlsson.noted.services.database.entities.Note

class NoteListAdapter(private val recyclerView: RecyclerView) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    init {
        ItemTouchHelper(TouchCallback()).attachToRecyclerView(recyclerView)
    }
    private var actualData: List<Note> = emptyList()
    var data: List<Note>
        get() = actualData
        set(value) {
            actualData = value
            notifyDataSetChanged()
        }
    var onClickListener: ((Note) -> Unit)? = null
    var onSwipeListener: ((Note) -> Unit)? = null

    private val viewOnClickListener = View.OnClickListener {
        val position = recyclerView.getChildAdapterPosition(it)
        if (position != RecyclerView.NO_POSITION) {
            val note = data[position]
            onClickListener?.invoke(note)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        view.setOnClickListener(viewOnClickListener)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val string = data[position].title
        (holder.itemView as TextView).text = string
    }

    override fun getItemCount() = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class TouchCallback : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = recyclerView.getChildAdapterPosition(viewHolder.itemView)
            if (position != RecyclerView.NO_POSITION) {
                val note = data[position]
                onSwipeListener?.invoke(note)
            }
        }

        override fun isLongPressDragEnabled(): Boolean = false

    }
}

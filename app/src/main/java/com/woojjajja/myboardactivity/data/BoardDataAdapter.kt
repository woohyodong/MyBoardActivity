package com.woojjajja.myboardactivity.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.woojjajja.myboardactivity.R
import kotlinx.android.synthetic.main.item_board.view.*

class BoardDataAdapter(val context: Context,val callback:(board: Board) -> Unit) : RecyclerView.Adapter<BoardDataAdapter.BoardViewHolder>(){


    private val layoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Board>()

    inner class BoardViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val title = view.findViewById<TextView>(R.id.text_title)

        fun bind(board: Board){
            title.tag = board.id
            title.text = board.title
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        var view = layoutInflater.inflate(R.layout.item_board,parent,false)
        return BoardViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.title.setOnClickListener {
            callback.invoke(item)
        }
    }

    fun setBoardData(items: MutableList<Board>){
        this.items = items
        notifyDataSetChanged()
    }
}
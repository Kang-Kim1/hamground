package com.example.myapplication.board

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import retrofit.Posting

class PostingAdapter : RecyclerView.Adapter<BoardViewHolder> {
    val postingList : ArrayList<Posting>

    constructor() {
        postingList = ArrayList<Posting>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val context = parent.context
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = inflater.inflate(R.layout.item_board, parent, false)

        return BoardViewHolder(context, view)
    }

    override fun onBindViewHolder(holderBoard: BoardViewHolder, position: Int) {
        val posting : Posting = postingList[position]
        holderBoard.titleTV.text = posting.title
        holderBoard.writerTV.text = posting.writer
        holderBoard.countsTV.text = posting.count
        holderBoard.likesTV.text = posting.likes
        holderBoard.repliesTV.text = "0"
        holderBoard.timeTV.text = posting.time

    }

    override fun getItemCount(): Int {
        return postingList.count()
    }

    fun setListData(items : ArrayList<Posting>) {
        postingList.clear()
        postingList.addAll(items)

        this.notifyDataSetChanged()
    }

}
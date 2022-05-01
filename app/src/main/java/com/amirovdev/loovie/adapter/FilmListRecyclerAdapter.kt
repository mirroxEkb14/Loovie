package com.amirovdev.loovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amirovdev.loovie.R
import com.amirovdev.loovie.model.Film

// the listener is passed so we can process clicks from Activity
class FilmListRecyclerAdapter(var data: ArrayList<Film>, private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // returns the amount of RecyclerView elements
    override fun getItemCount() = data.size

    // binds the ViewHolder and passes the 'inflated' markup of the Film
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false))
    }

    // binding of the fields from the Film object to View from film_item.xml
    // (the binding of the Film from our base to ViewHolder)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // check what RecyclerView we have here
        when (holder) {
            is FilmViewHolder -> {
                // pass the object from the base with a certain position
                holder.bind(data[position])

                // process the click on the whole element
                holder.itemView.findViewById<CardView>(R.id.item_container).setOnClickListener {
                    clickListener.click(data[position])
                }
            }
        }
    }

    // for adding elements to our list
    fun addItems(adapter: FilmListRecyclerAdapter) {
        val newList = arrayListOf<Film>()
        newList.addAll(adapter.data)
        updateData(newList, adapter)
    }

    // a temporary method of adding elements
    fun addItemsTemporary(list: List<Film>) {
        // clear all the list
        data.clear()
        // add a new one
        data.addAll(list)
        // notify RecyclerView that a new element has come and it has to bind everything again
        notifyDataSetChanged()
    }

    // updates the data in RecyclerView
    private fun updateData(newList: ArrayList<Film>, adapter: FilmListRecyclerAdapter) {
        val oldList = adapter.data // get the old list
        val productDiff = FilmDiff(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(productDiff)
        adapter.data = newList // set a new list
        diffResult.dispatchUpdatesTo(adapter) // the data changed in Adapter
    }

    // interface for processing the clicks
    interface OnItemClickListener {
        fun click(film: Film)
    }
}
package com.amirovdev.loovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
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
    fun addItems(list: List<Film>) {
        //Сначала очищаем(если не реализовать DiffUtils)
        data.clear()
        //Добавляем
        data.addAll(list)
        //Уведомляем RV, что пришел новый список, и ему нужно заново все "привязывать"
        notifyDataSetChanged()
    }

    // interface for processing the clicks
    interface OnItemClickListener {
        fun click(film: Film)
    }
}
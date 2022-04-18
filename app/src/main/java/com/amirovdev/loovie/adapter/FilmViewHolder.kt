package com.amirovdev.loovie.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amirovdev.loovie.R
import com.amirovdev.loovie.model.Film

/**
 * In the constructor the created layout(film_item.xml) is passed
 */

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Bind the View from layout to variables
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)

    // put the data from Film in our View
    fun bind(film: Film) {
        title.text = film.title
        poster.setImageResource(film.poster)
        description.text = film.description
    }
}
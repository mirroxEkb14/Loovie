package com.amirovdev.loovie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.amirovdev.loovie.model.Film

/**
 * Method of notification of data changes in the adapter via DiffUtil
 */

class FilmDiff(val oldList: ArrayList<Film> , val newList: ArrayList<Film> ): DiffUtil.Callback() {

    // the elements are equal if their 'ids' are equal
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    // the content is equal if all the fields are equal
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm.title == newFilm.title &&
                oldFilm.desc == newFilm.desc &&
                oldFilm.poster == newFilm.poster
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}
package com.amirovdev.loovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.amirovdev.loovie.model.Film

/**
 * A screen for film details: when the user clicks
 * on some movie in RecyclerView
 */

class DetailsFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    private fun processViews() {
        // get the Film from the passed Bundle
        val film = arguments?.get(FILM_KEY) as Film

        // initialize the Views
        val detailsToolbar = view?.findViewById<Toolbar>(R.id.details_toolbar)
        val detailsPoster = view?.findViewById<AppCompatImageView>(R.id.details_poster)
        val detailsDescription = view?.findViewById<TextView>(R.id.details_description)

        // assign the data to our DetailsActivity screen
        detailsToolbar?.title = film.title
        detailsPoster?.setImageResource(film.poster)
        detailsDescription?.text = film.desc
    }

    // key to the Bundle
    companion object {
        const val FILM_KEY = "film"
    }
}
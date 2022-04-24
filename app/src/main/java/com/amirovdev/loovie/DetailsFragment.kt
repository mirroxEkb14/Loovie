package com.amirovdev.loovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.amirovdev.loovie.model.Film
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A screen for film details: when the user clicks
 * on some movie in RecyclerView
 */

class DetailsFragment : Fragment() {

    lateinit var film: Film

    // in this method we get View, so we should initialize all the Views in here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processViews()

        // if the movie is in favorites or not, the icon differs
        details_fab_favorites.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )

        // listener for the favorite button
        details_fab_favorites.setOnClickListener {
            if (!film.isInFavorites) {
                details_fab_favorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                details_fab_favorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }

        // when clicking on 'share' button
        details_fab.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND // action with that it launches
            // film data
            intent.putExtra(Intent.EXTRA_TEXT, "Check out this film: ${film.title} \n\n ${film.desc}")
            //set MIME type, so that the System knows, what app to offer
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    private fun processViews() {
        // get the Film from the passed Bundle
        film = arguments?.get(FILM_KEY) as Film

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
package com.amirovdev.loovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.AppCompatImageView
import com.amirovdev.loovie.model.Film

/**
 * A screen for film details: when the user clicks
 * on some movie in RecyclerView
 */

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        processViews()
    }

    private fun processViews() {
        // get the Film from the passed Bundle
        val film = intent.extras?.get("film") as Film

        // initialize the Views
        val detailsToolbar = findViewById<Toolbar>(R.id.details_toolbar)
        val detailsPoster = findViewById<AppCompatImageView>(R.id.details_poster)
        val detailsDescription = findViewById<TextView>(R.id.details_description)

        // assign the data to our DetailsActivity screen
        detailsToolbar.title = film.title
        detailsPoster.setImageResource(film.poster)
        detailsDescription.text = film.description
    }
}
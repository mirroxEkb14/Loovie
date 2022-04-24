package com.amirovdev.loovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.amirovdev.loovie.DetailsFragment.Companion.FILM_KEY
import com.amirovdev.loovie.model.Film
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()

        // launch the Fragment when starting
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    // launch details Fragment
    fun launchDetailsFragment(film: Film) {
        val bundle = Bundle() // create a like
        bundle.putParcelable(FILM_KEY, film) // put the Film in a 'parcel'
        val fragment = DetailsFragment() // put details Fragment in variable
        fragment.arguments = bundle // attach the 'parcel' to Fragment

        // launch the Fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {
        val topToolbar = findViewById<MaterialToolbar>(R.id.top_toolbar)
        topToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val snackBar = Snackbar.make(main_layout, "Snackbar!", Snackbar.LENGTH_SHORT)
        bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    snackBar.setAction("Action") {
                        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    }.show()
                    true
                }
                R.id.favorites -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_placeholder, FavoritesFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.watch_later -> {
                    snackBar.setAction("Action") {
                        Toast.makeText(this, "Watch later", Toast.LENGTH_SHORT).show()
                    }.show()
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, "Selections", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    // double tap for exit the app
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, "Double tap for exit", Toast.LENGTH_SHORT).show()
            }
            backPressed = System.currentTimeMillis()

        } else {
            super.onBackPressed()
        }
    }

    // how much time the user has to tap one more time to exit
    companion object {
        const val TIME_INTERVAL = 2000
    }
}


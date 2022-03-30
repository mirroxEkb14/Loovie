package com.amirovdev.loovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMenuButtons()
    }

    // show Toast when clicking on the buttons
    private fun initMenuButtons() {
        val btnMenu = findViewById<Button>(R.id.btn_menu)
        btnMenu.setOnClickListener{
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
        }

        val btnFavorites = findViewById<Button>(R.id.btn_favorites)
        btnFavorites.setOnClickListener{
            Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
        }

        val btnWatchLater = findViewById<Button>(R.id.btn_watch_later)
        btnWatchLater.setOnClickListener{
            Toast.makeText(this, "Watch later", Toast.LENGTH_SHORT).show()
        }

        val btnSelections = findViewById<Button>(R.id.btn_selections)
        btnSelections.setOnClickListener{
            Toast.makeText(this, "Selections", Toast.LENGTH_SHORT).show()
        }

        val btnSettings = findViewById<Button>(R.id.btn_settings)
        btnSettings.setOnClickListener{
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }
    }
}
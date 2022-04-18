package com.amirovdev.loovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirovdev.loovie.adapter.FilmListRecyclerAdapter
import com.amirovdev.loovie.model.Film
import com.amirovdev.loovie.service.TopSpacingItemDecoration
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var filmsDataBase: List<Film>
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
        initFilmDataBase()
        initRecyclerView()
    }

    private fun initRecyclerView() {

        // find RecyclerView
        val mainRecycler = findViewById<RecyclerView>(R.id.main_recycler)
        mainRecycler.apply {

            // processing clicking on RecyclerView elements
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    // create a bundle and put in there an object with film data
                    val bundle = Bundle()

                    // the first parameter is a key by which we will search,
                    // the second parameter is a passing object
                    bundle.putParcelable("film", film)

                    // launch the Activity
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    // attach a Bundle to Intent
                    intent.putExtras(bundle)
                    // launch the Activity through the Intent
                    startActivity(intent)
                }
            })
            // assign adapter and layoutManager
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

            // apply decorators for margins
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        // put the BD in RecyclerView
        filmsAdapter.addItems(filmsDataBase)
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
        bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Watch later", Toast.LENGTH_SHORT).show()
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

    private fun initFilmDataBase() {
        filmsDataBase = listOf(
            Film("Collateral Beauty", R.drawable.collateral_beauty, getString(R.string.collateral_beauty_desc)),
            Film("A Beautiful Mind", R.drawable.a_beautiful_mind, getString(R.string.a_beautiful_mind_desc)),
            Film("Motherless Brooklyn", R.drawable.motherless_brooklyn, getString(R.string.motherless_brooklyn_desc)),
            Film("Black Swan", R.drawable.black_swan, getString(R.string.black_swan_desc)),
            Film("Catch me if You Can", R.drawable.catch_me_if_you_can, getString(R.string.catch_me_if_you_can_desc)),
            Film("Carlito's Way", R.drawable.carlitos_way, getString(R.string.carlitos_way_desc)),
            Film("Young Sheldon", R.drawable.young_sheldon, getString(R.string.young_sheldon_desc)),
            Film("Million Dollar Baby", R.drawable.million_dollar_baby, getString(R.string.million_dollar_baby_desc)),
            Film("The Big Lebowski", R.drawable.the_big_lebowski, getString(R.string.the_big_lebowski_desc)),
            Film("Coco", R.drawable.coco, getString(R.string.coco_desc)),
            Film("Requiem for a Dream", R.drawable.requiem_for_a_dream, getString(R.string.requiem_for_a_dream_desc)),
            Film("Righteous Kill", R.drawable.righteous_kill, getString(R.string.righteous_kill_desc))
        )
    }
}
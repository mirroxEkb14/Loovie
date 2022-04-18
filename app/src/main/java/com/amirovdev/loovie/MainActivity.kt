package com.amirovdev.loovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirovdev.loovie.adapter.FilmDiff
import com.amirovdev.loovie.adapter.FilmListRecyclerAdapter
import com.amirovdev.loovie.model.Film
import com.amirovdev.loovie.service.TopSpacingItemDecoration
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var filmsDataBase: ArrayList<Film>
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
            filmsAdapter = FilmListRecyclerAdapter(
                filmsDataBase,
                object : FilmListRecyclerAdapter.OnItemClickListener {
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
        filmsAdapter.addItems(filmsAdapter)
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
                R.id.favorites -> {
                    snackBar.setAction("Action") {
                        Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
                    }.show()
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

    private fun initFilmDataBase() {
        filmsDataBase = arrayListOf(
            Film(0, "Collateral Beauty", R.drawable.collateral_beauty, getString(R.string.collateral_beauty_desc)),
            Film(1, "A Beautiful Mind", R.drawable.a_beautiful_mind, getString(R.string.a_beautiful_mind_desc)),
            Film(2, "Motherless Brooklyn", R.drawable.motherless_brooklyn, getString(R.string.motherless_brooklyn_desc)),
            Film(3, "Black Swan", R.drawable.black_swan, getString(R.string.black_swan_desc)),
            Film(4, "Catch me if You Can", R.drawable.catch_me_if_you_can, getString(R.string.catch_me_if_you_can_desc)),
            Film(5, "Carlito's Way", R.drawable.carlitos_way, getString(R.string.carlitos_way_desc)),
            Film(6, "Young Sheldon", R.drawable.young_sheldon, getString(R.string.young_sheldon_desc)),
            Film(7,"Million Dollar Baby", R.drawable.million_dollar_baby, getString(R.string.million_dollar_baby_desc)),
            Film(8, "The Big Lebowski", R.drawable.the_big_lebowski, getString(R.string.the_big_lebowski_desc)),
            Film(9, "Coco", R.drawable.coco, getString(R.string.coco_desc)),
            Film(10, "Requiem for a Dream", R.drawable.requiem_for_a_dream, getString(R.string.requiem_for_a_dream_desc)),
            Film(11, "Righteous Kill", R.drawable.righteous_kill, getString(R.string.righteous_kill_desc))
        )
    }
}


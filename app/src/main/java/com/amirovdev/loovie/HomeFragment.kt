package com.amirovdev.loovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirovdev.loovie.adapter.FilmListRecyclerAdapter
import com.amirovdev.loovie.model.Film
import com.amirovdev.loovie.service.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var filmsDataBase: ArrayList<Film>
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFilmDataBase()
        initRecyclerView()

        // now the user can click everywhere in SearchView, not only on the lope
        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        // connect a listener of input text change in the SearchView
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            // when we click the 'Search' button on the keyword
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            // calls when the text changes
            override fun onQueryTextChange(newText: String?): Boolean {

                // if the input is empty, set to the Adapter the whole BD
                if (newText!!.isEmpty()) {
                    filmsAdapter.addItems(filmsAdapter)
                    return true
                }

                // filtering the list to search for suitable combinations
                val result = filmsDataBase.filter {
                    // for everything to work properly, we need to set the request and film name to lower case
                    it.title!!.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                // add to the Adapter
                filmsAdapter.addItemsTemporary(result)

                return true
            }
        })
    }

    private fun initRecyclerView() {

        // find RecyclerView
        val mainRecycler = view?.findViewById<RecyclerView>(R.id.main_recycler)
        mainRecycler?.apply {

            // processing clicking on RecyclerView elements
            filmsAdapter = FilmListRecyclerAdapter(filmsDataBase, object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })
            // assign adapter and layoutManager
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())

            // apply decorators for margins
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        // put the BD in RecyclerView
        filmsAdapter.addItems(filmsAdapter)
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
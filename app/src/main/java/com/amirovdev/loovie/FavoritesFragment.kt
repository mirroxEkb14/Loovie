package com.amirovdev.loovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirovdev.loovie.adapter.FilmListRecyclerAdapter
import com.amirovdev.loovie.model.Film
import com.amirovdev.loovie.service.TopSpacingItemDecoration

class FavoritesFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesList = arrayListOf<Film>(

        )

        view.findViewById<RecyclerView>(R.id.favorites_recycler)
            .apply {
                filmsAdapter = FilmListRecyclerAdapter(favoritesList, object : FilmListRecyclerAdapter.OnItemClickListener{
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
                adapter = filmsAdapter
                layoutManager = LinearLayoutManager(requireContext())

                // apply decorator for margins
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        // put out BD in RecyclerView
        filmsAdapter.addItems(filmsAdapter)
    }
}
package com.test.tmdb.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.test.tmdb.R
import com.test.tmdb.ui.main.adapter.MainAdapter
import com.test.tmdb.ui.main.pojo.GenreResponse
import com.test.tmdb.ui.main.pojo.GenresItem
import com.test.tmdb.ui.main.pojo.Movies
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by admin on 01/07/19.
 */
class MainActivity : AppCompatActivity(), IMainView {

    private var genresList: ArrayList<GenresItem>?=null
    private lateinit var adapter: MainAdapter
    private lateinit var iMainPresenter: IMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iMainPresenter = MainPresenterImp(this)


        initRecyclerView()

        iMainPresenter.getGenersList()


    }

    override fun onSuccess(genresList: GenreResponse) {
        this.genresList = (genresList.genres as ArrayList<GenresItem>?)!!
        initRecyclerView()
    }

    override fun onMovieGet(pos: Int, list: ArrayList<Movies>?) {
        adapter.updateChild(pos, list)
    }


    private fun initRecyclerView() {
        adapter = MainAdapter(this, genresList)
        recyclerView!!.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showWait() {
        progressBar!!.visibility = View.VISIBLE
        recyclerView!!.visibility = View.GONE

    }

    override fun removeWait() {
        progressBar!!.visibility = View.GONE
        recyclerView!!.visibility = View.VISIBLE
    }

    override fun onFailure(appErrorMessage: String) {
        Toast.makeText(this@MainActivity, appErrorMessage, Toast.LENGTH_SHORT).show()
    }
}

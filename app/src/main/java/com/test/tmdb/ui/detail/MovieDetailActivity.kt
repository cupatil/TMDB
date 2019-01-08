package com.test.tmdb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.tmdb.R
import com.test.tmdb.ui.detail.pojo.MovieDetailResponse
import kotlinx.android.synthetic.main.activity_movie_detail.*
/**
 * Created by admin on 01/07/19.
 */
class MovieDetailActivity : AppCompatActivity(), IMovieDetailView {

    private lateinit var movieId: String
    private lateinit var iMovieDetailPresenter: IMovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieId = intent.getStringExtra("movie_id")

        iMovieDetailPresenter = MovieDetailPresenterImp(this)

        iMovieDetailPresenter.getData(movieId)


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(movieDetailResponse: MovieDetailResponse) {


        supportActionBar!!.title = movieDetailResponse.title

        Glide.with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/w500/${movieDetailResponse.posterPath}")
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(ivMoviePoster)

        tvTitle.text = movieDetailResponse.title
        tvRelease.text = getString(R.string.releasing_date_txt) + " " + movieDetailResponse.releaseDate
        ratingBar.rating = (movieDetailResponse.voteAverage!! / 2).toFloat()
        tvVote.text = movieDetailResponse.voteCount.toString()
        tvRevenue.text = movieDetailResponse.revenue.toString()
        tvLanguage.text = movieDetailResponse.originalLanguage
        tvPopularity.text = movieDetailResponse.popularity.toString()
        tvSummary.text = movieDetailResponse.overview
    }

    override fun showWait() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun removeWait() {
        progressBar!!.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {
        Toast.makeText(this@MovieDetailActivity, appErrorMessage, Toast.LENGTH_SHORT).show()

    }

}
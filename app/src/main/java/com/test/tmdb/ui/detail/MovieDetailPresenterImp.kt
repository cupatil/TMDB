package com.test.tmdb.ui.detail

import com.test.tmdb.networking.ApiClient
import com.test.tmdb.networking.NetworkService
import com.test.tmdb.ui.detail.pojo.MovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by admin on 01/07/19.
 */
class MovieDetailPresenterImp(private val view: IMovieDetailView) : IMovieDetailPresenter {
    override fun getData(movieId: String) {
        view.showWait()

        val apiService = ApiClient.client.create(NetworkService::class.java)

        val call = apiService.getMovieDetails(movieId)

        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                view.removeWait()
                view.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                view.removeWait()
                view.onFailure(t.message!!)
            }
        })
    }


}



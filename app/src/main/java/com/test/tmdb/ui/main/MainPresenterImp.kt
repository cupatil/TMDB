package com.test.tmdb.ui.main

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.test.tmdb.networking.ApiClient
import com.test.tmdb.networking.NetworkService
import com.test.tmdb.ui.main.pojo.GenreResponse
import com.test.tmdb.ui.main.pojo.MoviesDataList
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by admin on 01/07/19.
 */
class MainPresenterImp(private val view: IMainView) : IMainPresenter {


    override fun getGenersList() {
        view.showWait()

        val apiService = ApiClient.client.create(NetworkService::class.java)

        val call = apiService.genresList()

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                view.removeWait()
                try {

                    val responseGenre = Gson().fromJson(response.body().toString(), GenreResponse::class.java)
                    view.onSuccess(responseGenre!!)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                view.removeWait()
                view.onFailure(t.message!!)
            }
        })
    }

    override fun getMovieList(pos: Int, genreId: Int?) {
        val apiService = ApiClient.client.create(NetworkService::class.java)

        val call = apiService.getGenreMovies(genreId)

        call.enqueue(object : Callback<MoviesDataList> {
            override fun onResponse(call: Call<MoviesDataList>, response: Response<MoviesDataList>) {

                view.removeWait()
                try {

                    Log.e("getMovieList", response.body().toString())

                                      view.onMovieGet(pos, response.body()!!.results)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<MoviesDataList>, t: Throwable) {
                view.removeWait()
                view.onFailure(t.message!!)
            }
        })
    }
}


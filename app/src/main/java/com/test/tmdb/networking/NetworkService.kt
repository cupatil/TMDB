package com.test.tmdb.networking

import com.google.gson.JsonObject
import com.test.tmdb.BuildConfig
import com.test.tmdb.ui.main.pojo.MoviesDataList
import com.test.tmdb.ui.detail.pojo.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by admin on 01/07/19.
 */
interface NetworkService {

    @GET("genre/movie/list?api_key=${BuildConfig.TMDB_API_KEY}")
    fun genresList(): Call<JsonObject>

    @GET("genre/{genre_id}/movies?api_key=${BuildConfig.TMDB_API_KEY}")
    fun getGenreMovies(@Path("genre_id") genre_id: Int?): Call<MoviesDataList>

    @GET("movie/{movie_id}?api_key=${BuildConfig.TMDB_API_KEY}")
    fun getMovieDetails(@Path("movie_id") movie_id: String?): Call<MovieDetailResponse>



}

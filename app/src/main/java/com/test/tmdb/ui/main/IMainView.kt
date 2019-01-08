package com.test.tmdb.ui.main


import com.test.tmdb.base.IBaseView
import com.test.tmdb.ui.main.pojo.GenreResponse
import com.test.tmdb.ui.main.pojo.Movies

/**
 * Created by admin on 01/07/19.
 */
interface IMainView : IBaseView {


    fun onSuccess(genresList: GenreResponse)

    fun onMovieGet(pos: Int, list: ArrayList<Movies>?)

}

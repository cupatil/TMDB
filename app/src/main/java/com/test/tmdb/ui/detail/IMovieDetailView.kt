package com.test.tmdb.ui.detail


import com.test.tmdb.base.IBaseView
import com.test.tmdb.ui.detail.pojo.MovieDetailResponse

/**
 * Created by admin on 01/07/19.
 */

interface IMovieDetailView : IBaseView {


    fun onSuccess(movieDetailResponse: MovieDetailResponse)


}

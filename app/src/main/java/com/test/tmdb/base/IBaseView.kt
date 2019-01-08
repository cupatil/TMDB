package com.test.tmdb.base

/**
 * This is base view interface for all the view in mvp
 * version code: 1.0
 */

interface IBaseView {

    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

}

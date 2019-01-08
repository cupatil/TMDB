package com.test.tmdb.networking


import com.test.tmdb.TMDBApplication
import com.test.tmdb.R
import java.io.IOException

/**
 * Created by admin on 01/07/19.
 */

class NoConnectivityException : IOException() {

    override val message: String?
        get() = TMDBApplication.instance.getString(R.string.internet_error)

}

package com.test.tmdb.utils

import android.content.Context
import com.idbh.utils.ConnectionDetector


/**
 * Created by admin on 1/12/2016.
 */
object ProjectUtilities {
    private var isInternetPresent: Boolean? = false
    private lateinit var cd: ConnectionDetector


    // This method is for checking internet connection
    fun checkInternetAvailable(mContext: Context): Boolean {
        cd = ConnectionDetector(mContext)
        isInternetPresent = cd.isConnectingToInternet

        return isInternetPresent as Boolean

    }


}

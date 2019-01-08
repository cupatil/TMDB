package com.test.tmdb.networking

import com.test.tmdb.TMDBApplication
import com.test.tmdb.utils.ProjectUtilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by admin on 01/07/19.
 */
class ConnectivityInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!ProjectUtilities.checkInternetAvailable(TMDBApplication.instance)) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

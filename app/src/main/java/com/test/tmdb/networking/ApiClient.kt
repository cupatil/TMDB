package com.test.tmdb.networking

import com.test.tmdb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

/**
 * Created by admin on 01/07/19.
 */
object ApiClient {
    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(ConnectivityInterceptor())
                httpClient.connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                httpClient.addInterceptor(logging)
                retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(BuildConfig.TMDB_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

}
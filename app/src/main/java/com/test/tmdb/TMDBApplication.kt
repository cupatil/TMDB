package com.test.tmdb

import android.app.Application
import com.test.tmdb.networking.ApiClient
import com.test.tmdb.networking.NetworkService


/**
 * Created by admin on 01/07/2019.
 * This is an Application class
 * version code: 1.0
 */

class TMDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        netWorkServiceObject = ApiClient.client.create(NetworkService::class.java)

    }

    companion object {
        lateinit var instance: TMDBApplication private set
        lateinit var netWorkServiceObject: NetworkService
    }
}

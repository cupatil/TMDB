package com.test.tmdb.ui.main.pojo

import com.google.gson.annotations.SerializedName

import java.io.Serializable


class MoviesDataList : Serializable {

    @SerializedName("results")
    var results: ArrayList<Movies>? = null


}

package com.test.tmdb.ui.main.pojo

import com.google.gson.annotations.SerializedName

class Movies {

    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("id")
    var id: Int = 0

}

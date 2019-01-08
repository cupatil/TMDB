package com.test.tmdb.ui.main.pojo

import com.google.gson.annotations.SerializedName

data class GenreResponse(

        @field:SerializedName("genres")
        val genres: List<GenresItem?>? = null
)
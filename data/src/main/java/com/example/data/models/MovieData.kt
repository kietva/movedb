package com.example.data.models

import com.example.domain.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id") val id: Long,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("backdrop_path") val backdropPath: String,
)

fun MovieData.toEntity() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
)
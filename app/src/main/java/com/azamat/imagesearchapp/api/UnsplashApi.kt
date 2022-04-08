package com.azamat.imagesearchapp.api

import androidx.core.os.BuildCompat
import com.azamat.imagesearchapp.BuildConfig
import dagger.hilt.DefineComponent
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    companion object{
        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = com.azamat.imagesearchapp.BuildConfig.UNSPLASH_ACCESS_KEY
    }

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int,

    ):UnsplashResponse
}
package com.example.retrofitandcoilpracticeapp.data

import com.example.retrofitandcoilpracticeapp.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val marsPhotosRepositary:MarsPhotosRepositary
}
class DefaultAppContainer:AppContainer{
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"
    private val retrofit:Retrofit=Retrofit.Builder().addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(
            baseUrl
        ).build()
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
    override val marsPhotosRepositary: MarsPhotosRepositary by lazy {
        NetworkMarsPhotoRepository(retrofitService)
    }
}
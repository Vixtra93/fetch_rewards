package com.project.fetchrewards.remote

import com.project.fetchrewards.remote.models.Item
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {
    @GET("hiring.json")
    suspend fun getItems() : Response<List<Item>>

}
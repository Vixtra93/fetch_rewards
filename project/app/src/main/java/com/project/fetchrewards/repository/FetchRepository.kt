package com.project.fetchrewards.repository

import com.project.fetchrewards.utils.Resource
import com.project.fetchrewards.remote.models.Item
import com.project.fetchrewards.remote.FetchRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchRepository @Inject constructor(
    private val remoteDataSource: FetchRemoteDataSource
) {
    suspend fun getItems(): Resource<List<Item>> = withContext(Dispatchers.IO) {
        val response = remoteDataSource.getItems()
        if (response.status == Resource.Status.SUCCESS) {
            Resource.success(response.data) as Resource<List<Item>>

        } else {
            Resource.error(response.message!!)
        }
    }
}


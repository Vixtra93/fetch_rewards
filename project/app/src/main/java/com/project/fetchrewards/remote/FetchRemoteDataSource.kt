package com.project.fetchrewards.remote

import javax.inject.Inject

 class FetchRemoteDataSource @Inject constructor(
    private val fetchService: FetchService
) : BaseDataSource() {
    suspend fun getItems() = getResult { fetchService.getItems() }

}
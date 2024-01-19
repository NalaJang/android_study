package com.example.retrofit2ex.data.source

import com.example.retrofit2ex.data.dto.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET
    suspend fun getUserList(@Query("page") page: Int): UserListResponse
}
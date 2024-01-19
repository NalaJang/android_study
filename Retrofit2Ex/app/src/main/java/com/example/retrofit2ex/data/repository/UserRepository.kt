package com.example.retrofit2ex.data.repository

import com.example.retrofit2ex.data.entity.User
import com.example.retrofit2ex.data.source.NetworkService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkService: NetworkService
) {
   // page 에 있는 user 데이터를 가지고 온다.
    suspend fun getUserList(page: Int): List<User> {
        return networkService.getUserList(page).data
    }
}
package com.example.retrofit2ex.data.repository

import com.example.retrofit2ex.data.entity.User
import com.example.retrofit2ex.data.local.UserDao
import com.example.retrofit2ex.data.source.NetworkService
import com.example.retrofit2ex.domain.model.UserModel
import javax.inject.Inject

// 매개변수인 NetworkService 는 UserRepository 의 종속(dependency) 항목이다.
// 따라서 Hilt 는 NetworkService 를 제공하는 방법도 알고 있어야 한다.
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val userDao: UserDao
) {
   // page 에 있는 user 데이터를 가지고 온다.
    suspend fun getUserList(page: Int): List<User> {
        return networkService.getUserList(page).data
    }

    suspend fun getUsers(): List<UserModel> = userDao.getAllUser()

    suspend fun saveUserToDB(userModel: UserModel) = userDao.saveUserToDB(userModel)

    suspend fun updateFavorite(isFavorite: Boolean, id: Int) = userDao.updateFavorite(isFavorite, id)
}
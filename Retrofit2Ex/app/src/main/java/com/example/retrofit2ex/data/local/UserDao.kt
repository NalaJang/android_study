package com.example.retrofit2ex.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.retrofit2ex.domain.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<UserModel>
}
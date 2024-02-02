package com.example.retrofit2ex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retrofit2ex.domain.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(userModel: UserModel)
}
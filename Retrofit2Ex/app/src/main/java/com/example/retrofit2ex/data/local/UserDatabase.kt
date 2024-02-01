package com.example.retrofit2ex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retrofit2ex.domain.model.UserModel

@Database(entities = [UserModel::class], version = 1)
// 추상 클래스와 추상 메소드로 만들고 module 에서 구현한다.
abstract class UserDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
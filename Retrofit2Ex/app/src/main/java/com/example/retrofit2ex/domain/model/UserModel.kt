package com.example.retrofit2ex.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey
    val id: Int,
    val email: String,
    val name: String,
    val avatar: String
)

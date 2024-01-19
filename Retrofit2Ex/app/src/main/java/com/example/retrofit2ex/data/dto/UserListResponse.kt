package com.example.retrofit2ex.data.dto

import com.example.retrofit2ex.data.entity.User

data class UserListResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)

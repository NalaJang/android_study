package com.example.retrofit2ex.domain.translator

import com.example.retrofit2ex.data.entity.User
import com.example.retrofit2ex.domain.model.UserModel

// dto 데이터를 어떻게 변환할 지 정의
/**
 * Translates [User] entity to [UserModel] to use in views.
 */
object UserTranslator {
    fun List<User>.toUserModelList() = map {
        UserModel(it.id, it.email, it.first_name + " " + it.last_name, it.avatar)
    }
}
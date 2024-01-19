package com.example.retrofit2ex.domain.usecase

import com.example.retrofit2ex.data.repository.UserRepository
import com.example.retrofit2ex.domain.model.UserModel
import com.example.retrofit2ex.domain.translator.UserTranslator.toUserModelList
import javax.inject.Inject

class GetUserListUserCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(page: Int): List<UserModel> {
        return userRepository.getUserList(page).toUserModelList()
    }
}
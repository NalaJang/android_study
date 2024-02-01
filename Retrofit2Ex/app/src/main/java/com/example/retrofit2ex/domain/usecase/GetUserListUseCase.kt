package com.example.retrofit2ex.domain.usecase

import com.example.retrofit2ex.data.repository.UserRepository
import com.example.retrofit2ex.domain.model.UserModel
import com.example.retrofit2ex.domain.translator.UserTranslator.toUserModelList
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    // invoke : 이름 없이 간편하게 호출될 수 있는 함수
    // operator(연산자) 키워드를 통해 invoke 함수를 부를 수 있다.
    suspend operator fun invoke(page: Int): List<UserModel> {
        return userRepository.getUserList(page).toUserModelList()
    }

    suspend operator fun invoke(): List<UserModel> = userRepository.getUsers()
}
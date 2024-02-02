package com.example.retrofit2ex.ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit2ex.domain.model.UserModel
import com.example.retrofit2ex.domain.usecase.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserListUseCase: GetUserListUseCase): ViewModel() {

    // StateFlow : 현재 상태와 새로운 상태 업데이트를 수집기에 내보내는 관찰 가능한 상태 홀더 흐름이다.
    private  val _userList = MutableStateFlow<List<UserModel>>(listOf())
    val userList: StateFlow<List<UserModel>> = _userList

    init {
        readUserList()
        getUserListFromLocalDB()
    }

    private fun readUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            // value 를 통해 현재 상태 값을 읽는다.
            // invoke 함수를 이용해 GetUserListUseCase 의 함수를 함수 이름 없이 간편하게 불러왔다.
            _userList.value = getUserListUseCase(1)
        }
    }

    private fun getUserListFromLocalDB() {
        viewModelScope.launch(Dispatchers.IO) {
            _userList.value = getUserListUseCase.invoke()
        }
    }

    fun saveUserToDB(userModel: UserModel) {
        viewModelScope.launch {
            getUserListUseCase.invoke(userModel)
        }
    }

    fun updateFavorite(isFavorite: Boolean, id: Int) {
        viewModelScope.launch {
            getUserListUseCase.invoke(isFavorite, id)
        }
    }
}
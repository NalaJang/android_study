package com.example.retrofit2ex.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit2ex.data.repository.UserRepository
import com.example.retrofit2ex.domain.model.UserModel
import com.example.retrofit2ex.domain.usecase.GetUserListUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserListUserCase: GetUserListUserCase): ViewModel() {

    // StateFlow : 현재 상태와 새로운 상태 업데이트를 수집기에 내보내는 관찰 가능한 상태 홀더 흐름이다.
    private  val _userList = MutableStateFlow<List<UserModel>>(listOf())
    val userList: StateFlow<List<UserModel>> = _userList

    init {
        readUserList()
    }

    private fun readUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            // value 를 통해 현재 상태 값을 읽는다.
            _userList.value = getUserListUserCase(1)
        }
    }
}
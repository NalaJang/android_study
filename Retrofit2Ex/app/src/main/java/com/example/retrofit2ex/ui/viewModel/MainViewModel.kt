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

    private  val _userList = MutableStateFlow<List<UserModel>>(listOf())
    val userList: StateFlow<List<UserModel>> = _userList

    init {
        readUserList()
    }

    private fun readUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            _userList.value = getUserListUserCase(1)
        }
    }
}
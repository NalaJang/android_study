package com.example.retrofit2ex.ui.presenter

import android.util.Log
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
    }

    private fun readUserList() {
        viewModelScope.launch(Dispatchers.IO) {

            val localData = getUserListUseCase.invoke()

            // DB 가 비어있을 때(앱의 첫 실행 등)만 초기 데이터를 저장하도록 했다.
            if( localData.isEmpty() ) {
                // value 를 통해 현재 상태 값을 읽는다.
                // invoke 함수를 이용해 GetUserListUseCase 의 함수를 함수 이름 없이 간편하게 불러왔다.
                _userList.value = getUserListUseCase(1)
                // 초기 데이터 저장
                saveUserToDB(_userList.value)

            } else {
                _userList.value = getUserListUseCase.invoke()
            }
        }
    }

    private fun getUserListFromLocalDB() {
        viewModelScope.launch(Dispatchers.IO) {
            _userList.value = getUserListUseCase.invoke()
        }
    }

    private fun saveUserToDB(aa: List<UserModel>) {
        for( i in aa.indices) {
            viewModelScope.launch {
                getUserListUseCase.invoke(aa[i])
            }
        }
    }

    // Dispatchers.IO : I/O 작업에 사용된다. 파일 읽기/쓰기, 네트워크 호출과 같은 블로킹되는 작업에 유용하다.
    // (Dispatchers.IO) 를 사용하여 백그라운드에서 스레드가 실행되도록 한다.
    // 이렇게 하면 메인 스레드를 차단하지 않고 작업을 수행할 수 있어 앱의 응답성이 향상된다.
    fun updateFavorite(isFavorite: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserListUseCase.invoke(isFavorite, id)
        }
    }
}
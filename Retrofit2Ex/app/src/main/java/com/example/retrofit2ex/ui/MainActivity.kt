package com.example.retrofit2ex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.retrofit2ex.R
import com.example.retrofit2ex.databinding.ActivityMainBinding
import com.example.retrofit2ex.ui.adapter.UserAdapter
import com.example.retrofit2ex.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            // layoutManager 초기화, viewModel 과 adapter 를 dataBinding 의 <data> 영역으로 넘기고
            // xml 에서 관련 로직을 처리한 덕분에 뷰의 코드가 간략해졌다.
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.adapter = UserAdapter()
        }
    }
}
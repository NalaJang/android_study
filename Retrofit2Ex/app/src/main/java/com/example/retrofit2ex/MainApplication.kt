package com.example.retrofit2ex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Hilt 셋업의 필수 요소. 컴파일 타임 시 표준 컴포넌트 빌딩에 필요한 클래스들을 초기화한다.
class MainApplication : Application()
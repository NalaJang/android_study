package com.example.retrofit2ex.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.retrofit2ex.data.local.UserDao
import com.example.retrofit2ex.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    // UserDatabase 는 Room 에서 생성하지 않는, 프로젝트에서 소유하지 않는 클래스이기 때문에
    // @Provides 를 사용하여 제공한다.
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.getUserDao()
    // Hilt 에 UserDao 인스턴스를 제공할 때 database.getUserDao()가 실행되어야 한다고 알린다.
    // 종속 항목인 UserDatabase 생성 방법은 provideDatabase()에서 알려주고 있다.
}
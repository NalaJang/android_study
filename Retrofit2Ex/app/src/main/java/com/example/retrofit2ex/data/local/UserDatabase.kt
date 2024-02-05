package com.example.retrofit2ex.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.retrofit2ex.domain.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

@Database(entities = [UserModel::class], version = 1)
// 추상 클래스와 추상 메소드로 만들고 module 에서 구현한다.
abstract class UserDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao


    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "user_db")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                getInstance(context).getUserDao().saveUserToDB(
                                    UserModel()
                                )
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()

            }.also {
                INSTANCE = it
            }
        }
    }
}
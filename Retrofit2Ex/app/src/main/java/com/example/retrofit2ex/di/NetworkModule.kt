package com.example.retrofit2ex.di

import com.example.retrofit2ex.data.source.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                            .addNetworkInterceptor(httpLoggingInterceptor)
                            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /*
        @Provides 를 붙여서 Retrofit 생성하는 방법을 Hilt에 알리기!

        Hilt는 @Provides 가 달린 provideRetrofit()가
        * 반환 타입인 Retrofit 를 제공함을 알 수 있다.
        * 매개변수인 GsonConverterFactory, OkHttpClient 를 통해 provideOkHttpClient(),
        provideGsonConverterFactory()에 종속되었음을 알 수 있다.
        * 메소드의 본문은 Retroit을 제공하는 방법을 알려준다.
        * 따라서 Hilt는 Retrofit를 필요할 때마다 provideRetrofit()를 실행해야 한다.
    */
    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(gsonConverterFactory)
                        .client(okHttpClient)
                        .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }


    companion object {
        private const val BASE_URL = "https://reqres.in"
    }
}
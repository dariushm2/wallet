package com.darius.wallet.network.di

import android.content.Context
import com.darius.wallet.network.ApiService
import com.darius.wallet.network.ConnectivityHandler
import com.darius.wallet.network.repositories.RecipesRepo
import com.darius.wallet.network.repositories.RestfulRecipeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/")
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRecipesRepo(): RecipesRepo =
        RestfulRecipeRepo(provideApiService())

    @Provides
    @Singleton
    fun provideConnectivityHandler(@ApplicationContext appContext: Context) =
        ConnectivityHandler(appContext)
}

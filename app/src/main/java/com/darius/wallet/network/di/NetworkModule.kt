package com.darius.wallet.network.di

import android.content.Context
import com.darius.wallet.BuildConfig
import com.darius.wallet.network.ApiService
import com.darius.wallet.network.ConnectivityHandler
import com.darius.wallet.network.cache.RecipeDataBase
import com.darius.wallet.network.repositories.RecipesRepo
import com.darius.wallet.network.repositories.RestfulRecipeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRecipesRepo(@ApplicationContext appContext: Context): RecipesRepo =
        RestfulRecipeRepo(provideApiService(), provideRecipeDao(appContext))

    @Provides
    @Singleton
    fun provideConnectivityHandler(@ApplicationContext appContext: Context) =
        ConnectivityHandler(appContext)

    @Provides
    @Singleton
    fun provideRecipeDao(@ApplicationContext appContext: Context) =
        RecipeDataBase.getDatabase(appContext).recipeDao()

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher =
        Dispatchers.Default
}

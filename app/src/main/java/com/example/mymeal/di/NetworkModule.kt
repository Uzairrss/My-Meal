package com.example.mymeal.di

import com.example.mymeal.area.data.remote.AreaApi
import com.example.mymeal.area.repository.AreaRepository
import com.example.mymeal.area.repository.IAreaRepository
import com.example.mymeal.area.usecase.AreaUseCase
import com.example.mymeal.area.usecase.IAreaUseCase
import com.example.mymeal.category.data.remote.CategoryApi
import com.example.mymeal.category.repository.CategoryRepository
import com.example.mymeal.category.repository.ICategoryRepository
import com.example.mymeal.category.usecase.CategoryUseCase
import com.example.mymeal.category.usecase.ICategoryUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAreaApi(retrofit: Retrofit): AreaApi {
        return retrofit.create(AreaApi::class.java)
    }

    /*@Provides
    @Singleton
    fun provideDetailApi(retrofit: Retrofit): DetailApi {
        return retrofit.create(DetailApi::class.java)
    }*/

    /*   @Provides
       @Singleton
       fun provideDispatcher(): CoroutineDispatcher {
           return Dispatchers.IO
       }*/

    @Module
    @InstallIn(SingletonComponent::class)
    interface getNetworkModule {

        @Binds
        @Singleton
        fun getCategoryRepository(repo: CategoryRepository): ICategoryRepository

        @Binds
        @Singleton
        fun getCategoryUseCase(uc: CategoryUseCase): ICategoryUseCase


        @Binds
        @Singleton
        fun getAreaRepository(repo: AreaRepository): IAreaRepository

        @Binds
        @Singleton
        fun getAreaUseCase(uc: AreaUseCase): IAreaUseCase

        /*@Binds
        @Singleton
        fun getDetailRepository(repo: DetailRepository): IDetailRepository

        @Binds
        @Singleton
        fun getDetailUseCase(uc: DetailUseCase): IDetailUseCase*/
    }
}
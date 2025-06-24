package com.example.ecommerceproject.di

import com.example.ecommerceproject.data.datasource.CommerceDataSource
import com.example.ecommerceproject.data.repo.CommerceRepository
import com.example.ecommerceproject.retrofit.ApiUtils
import com.example.ecommerceproject.retrofit.CommerceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCommerceRepository(commerceDataSource: CommerceDataSource):CommerceRepository{
        return CommerceRepository(commerceDataSource)
    }

    @Provides
    @Singleton
    fun provideCommerceDataSource(commerceDao: CommerceDao):CommerceDataSource{
        return CommerceDataSource(commerceDao)
    }

    @Provides
    @Singleton
    fun provideComerceDao():CommerceDao{
        return ApiUtils.getProductsDao()
    }
}
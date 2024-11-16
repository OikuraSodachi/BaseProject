package com.todokanai.baseproject.di

import android.content.Context
import com.todokanai.baseproject.data.room.MyDatabase
import com.todokanai.baseproject.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMyDatabase(@ApplicationContext context: Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context):DataStoreRepository{
        return DataStoreRepository(context)
    }
}
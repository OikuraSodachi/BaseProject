package com.todokanai.baseproject.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.todokanai.baseproject.abstracts.MyDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository @Inject constructor(appContext:Context):MyDataStore(appContext){
    companion object{
        val DATASTORE_STRING = stringPreferencesKey("datastore_sort_by")
    }
    suspend fun saveString(value:String) = DATASTORE_STRING.save(value)

    suspend fun getString() = DATASTORE_STRING.value()

    val stringFlow = DATASTORE_STRING.flow()
}
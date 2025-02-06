package com.todokanai.baseproject.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.todokanai.baseproject.abstracts.MyDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository @Inject constructor(appContext:Context):MyDataStore(appContext){
    companion object{
        private val DATASTORE_STRING = stringPreferencesKey("datastore_sort_by")
        private const val DATASTORE_STRING_DEFAULT_VALUE : String = "default"
    }
    suspend fun saveString(value:String) = DATASTORE_STRING.save(value)
    suspend fun getString() = DATASTORE_STRING.value()
    suspend fun getStringNonNull() = DATASTORE_STRING.notNullValue(DATASTORE_STRING_DEFAULT_VALUE)
    val stringFlow = DATASTORE_STRING.flow()
    val stringFlowNotNull = DATASTORE_STRING.notNullFlow(DATASTORE_STRING_DEFAULT_VALUE)
}
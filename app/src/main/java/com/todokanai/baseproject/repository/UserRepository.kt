package com.todokanai.baseproject.repository

import com.todokanai.baseproject.data.room.User
import com.todokanai.baseproject.data.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao:UserDao) {

    fun getAll() = userDao.getAll()

    suspend fun getAllNonFlow() = userDao.getAllNonFlow()

    fun insert(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insert(user)
        }
    }

    fun delete(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.delete(user)
        }
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
        }
    }

    fun getUserByIndex(no:Long) = userDao.getUserByIndex(no)
}
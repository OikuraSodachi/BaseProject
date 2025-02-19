package com.todokanai.baseproject.repository

import com.todokanai.baseproject.data.room.User
import com.todokanai.baseproject.data.room.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao:UserDao) {

    fun getAll() = userDao.getAll()

    suspend fun getAllNonFlow() = userDao.getAllNonFlow()

    suspend fun insert(user: User) = userDao.insert(user)

    suspend fun delete(user: User) = userDao.delete(user)

    suspend fun deleteAll() = userDao.deleteAll()

    suspend fun update(number:Long,userValue:Int) = userDao.update(number,userValue)

    fun getUserByIndex(no:Long) = userDao.getUserByIndex(no)

    fun getUserByNumber(number:Long) = userDao.getUserByNumber(number)
}
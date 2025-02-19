package com.todokanai.baseproject.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("select * from room_user")
    fun getAll() : Flow<List<User>>

    @Query("select * from room_user")
    suspend fun getAllNonFlow() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user : User)

    @Delete
    suspend fun delete(user : User)

    @Query("delete from room_user")
    suspend fun deleteAll()

    /** room 에서 Index 기준 query **/
    @Query("select * from room_user where `no`=:no")
    fun getUserByIndex(no:Long) : Flow<User>

    /** User 의 "number" parameter 기준 query **/
    @Query("select * from room_user where `number`=:number")
    fun getUserByNumber(number:Long):Flow<User>

    /** User 의 "number" parameter 기준 update **/
    @Query("update room_user SET userValue=:userValue where `number` =:number")
    suspend fun update(number:Long, userValue: Int)

}
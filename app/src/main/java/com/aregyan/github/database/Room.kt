package com.aregyan.github.database

import androidx.lifecycle.LiveData
import androidx.room.*

/*
This is a Data access object and Room database which
*/
@Dao
interface UsersDao {

    // user List
    @Query("select * from DatabaseUserListItem")
    fun getDatabaseUsers(): LiveData<List<DatabaseUserListItem>>

    //Insert all into room db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseUserListItem>)

    // single user
    @Query("select * from DatabaseUserDetails WHERE user LIKE :user")
    fun getUserDetails(user: String): LiveData<DatabaseUserDetails>

    //Insert single user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(databaseUserDetails: DatabaseUserDetails)
}

//Room Database
@Database(entities = [DatabaseUserListItem::class, DatabaseUserDetails::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}
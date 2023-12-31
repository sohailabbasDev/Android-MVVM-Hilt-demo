package com.aregyan.github.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aregyan.github.database.UsersDatabase
import com.aregyan.github.database.asDomainModel
import com.aregyan.github.domain.UserListItem
import com.aregyan.github.network.UserListService
import com.aregyan.github.network.model.asDatabaseModel
//import timber.log.Timber
import javax.inject.Inject

//Users list repository
class UserListRepository @Inject constructor(
    private val userListService: UserListService,
    private val database: UsersDatabase
) {

    //val that has all the users stored
    val users: LiveData<List<UserListItem>> =
        Transformations.map(database.usersDao.getDatabaseUsers()) {
            it.asDomainModel()
        }

    //refresh fun to reload the user list
    suspend fun refreshUserList() {
        try {
            val users = userListService.getUserList()
            database.usersDao.insertAll(users.asDatabaseModel())
        } catch (e: Exception) {
            e.printStackTrace()
//            Timber.w(e)
        }
    }
}
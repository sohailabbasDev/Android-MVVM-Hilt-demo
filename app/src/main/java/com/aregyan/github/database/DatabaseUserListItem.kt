package com.aregyan.github.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aregyan.github.domain.UserListItem

//Entity that represents user list data for ROOM DB
@Entity
data class DatabaseUserListItem constructor(
    @PrimaryKey
    val id: Int,
    val avatar: String,
    val username: String
)

//Mapper to  convert DatabaseUserListItem list to UserListItem list
fun List<DatabaseUserListItem>.asDomainModel(): List<UserListItem> {
    return map {
        UserListItem(
            id = it.id,
            avatar = it.avatar,
            username = it.username
        )
    }
}
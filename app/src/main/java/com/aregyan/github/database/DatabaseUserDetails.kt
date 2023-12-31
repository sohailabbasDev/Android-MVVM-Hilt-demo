package com.aregyan.github.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aregyan.github.domain.UserDetails

//Entity data class for room DB
@Entity
data class DatabaseUserDetails constructor(
    @PrimaryKey
    val user: String,
    val avatar: String,
    val name: String,
    val userSince: String,
    val location: String
)

//Mapper that converts DatabaseUserDetails to UserDetails
fun DatabaseUserDetails.asDomainModel(): UserDetails {
    return UserDetails(
        user = user,
        avatar = avatar,
        name = name,
        userSince = userSince,
        location = location
    )
}
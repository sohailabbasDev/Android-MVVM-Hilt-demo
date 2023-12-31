package com.aregyan.github.network

import com.aregyan.github.network.model.NetworkUserListItem
import retrofit2.http.GET

interface UserListService {

    //gets the list of user API
    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUserList(): List<NetworkUserListItem>
}